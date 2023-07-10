package com.olus.olingo4.nnmrls.storage;

import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider;
import com.olus.olingo4.nnmrls.util.Olingo4Converter;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

import java.util.AbstractMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.*;

/**
 * Storage class to fetch {@link EntityCollection}
 *
 * @author Oleksii Usatov
 */
public class Olingo4Storage {
    private final MyBatisDao mybatisDao;

    public Olingo4Storage() {
        this.mybatisDao = new MyBatisDao();
    }

    /**
     * We need this because we'll receive string with "''" -  Olingo requires to specify string key with quotes,
     * e.g. 'some_id'
     *
     * @param id original Id received from request
     * @return Key without quotes
     */
    private static String getKeyValue(String id) {
        var key = id;
        if (id.length() >= 3) {
            key = id.substring(1, id.length() - 1);
        }
        return key;
    }

    /**
     * Check if specified property is key
     *
     * @param edmEntityType {@link EdmEntityType}
     * @param propertyName  property name
     * @return true if key otherwise false
     */
    private static boolean isKey(EdmEntityType edmEntityType,
                                 String propertyName) {
        List<EdmKeyPropertyRef> keyPropertyRefs = edmEntityType.getKeyPropertyRefs();
        for (EdmKeyPropertyRef propRef : keyPropertyRefs) {
            String keyPropertyName = propRef.getName();
            if (keyPropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fetch data for specified offset and limit ('skip' and 'top')
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public EntityCollection getData(EdmEntitySet edmEntitySet,
                                    int offset,
                                    int limit, List<String> columns) throws ODataApplicationException {

        var pragentsCollection = new EntityCollection();

        // Check for which EdmEntitySet the data is requested
        switch (edmEntitySet.getName()) {
            case ES_LOOKUPS_NAME:
            case ES_MEMBER_DATA_NAME:
            case ES_OFFICE_DATA_NAME:
            case ES_PROP_DATA_BUISINESS_NAME:
            case ES_PROP_DATA_CHARACT_NAME:
            case ES_PROP_DATA_HOA_NAME:
            case ES_PROP_DATA_LIST_NAME:
            case ES_PROP_DATA_LOC_NAME:
            case ES_PROP_DATA_STRUCT_NAME:
            case ES_PROP_DATA_TAX_NAME:
            case ES_PROP_DATA_TOUR_NAME:
                var fqn = ES_TO_EF.get(edmEntitySet.getName());
                var csdlEntity = NnmrlsEdmProvider.getCsdlEntityType(fqn);
                var key = csdlEntity.getKey().get(0).getName();
                return Olingo4Converter.convertListOfMaps(edmEntitySet.getName(),
                        key,
                        mybatisDao.selectAllEntities(NnmrlsEdmProvider.ES_TO_ET.get(edmEntitySet.getName()),
                                offset, limit, columns));
        }
        return pragentsCollection;
    }

    /**
     * Fetch data for specified offset and limit ('skip' and 'top')
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public Optional<Entity> getDataByKeys(EdmEntitySet edmEntitySet,
                                          List<UriParameter> keyParams,
                                          List<String> columns) {
        var edmEntityType = edmEntitySet.getEntityType();

        // This is only required if we have more than one Entity Type
        switch (edmEntityType.getName()) {
            case ET_LOOKUPS_NAME:
            case ET_MEMBER_DATA_NAME:
            case ET_OFFICE_DATA_NAME:
            case ET_PROP_DATA_BUISINESS_NAME:
            case ET_PROP_DATA_CHARACT_NAME:
            case ET_PROP_DATA_HOA_NAME:
            case ET_PROP_DATA_LIST_NAME:
            case ET_PROP_DATA_LOC_NAME:
            case ET_PROP_DATA_STRUCT_NAME:
            case ET_PROP_DATA_TAX_NAME:
            case ET_PROP_DATA_TOUR_NAME:
                var key = keyParams.get(0);
                var result = mybatisDao.selectEntity(edmEntityType.getName(), key.getName(), getKeyValue(key.getText()),
                        columns);
                return result.map(stringObjectMap -> Olingo4Converter.convertMap(ET_TO_ES.get(edmEntityType.getName()),
                        key.getName(), stringObjectMap
                ));
            default:
                return Optional.empty();
        }
    }

    /**
     * Create entity
     *
     * @param edmEntitySet   {@link EdmEntitySet}
     * @param entityToCreate {@link Entity}
     * @return created {@link Entity}
     */
    public Entity createEntityData(EdmEntitySet edmEntitySet,
                                   Entity entityToCreate) throws ODataApplicationException {
        EdmEntityType edmEntityType = edmEntitySet.getEntityType();

        // We have only one key!
        var key = edmEntityType.getKeyPropertyRefs().get(0).getName();
        var params = Olingo4Converter.convertEntity(entityToCreate);

        switch (edmEntityType.getName()) {
            case ET_LOOKUPS_NAME:
            case ET_MEMBER_DATA_NAME:
            case ET_OFFICE_DATA_NAME:
            case ET_PROP_DATA_BUISINESS_NAME:
            case ET_PROP_DATA_CHARACT_NAME:
            case ET_PROP_DATA_HOA_NAME:
            case ET_PROP_DATA_LIST_NAME:
            case ET_PROP_DATA_LOC_NAME:
            case ET_PROP_DATA_STRUCT_NAME:
            case ET_PROP_DATA_TAX_NAME:
            case ET_PROP_DATA_TOUR_NAME:
                return Olingo4Converter.convertMap(NnmrlsEdmProvider.ES_TO_ET.get(edmEntityType.getName()),
                        key, mybatisDao.insertEntity(edmEntityType.getName(), key, params));
            default:
                throw new ODataApplicationException("Not supported.", HttpStatusCode.NOT_ACCEPTABLE.getStatusCode(), Locale.ROOT);
        }
    }

    /**
     * Update entity
     *
     * @param edmEntitySet  {@link EdmEntitySet}
     * @param keyPredicates list of {@link UriParameter}
     * @param entity        {@link Entity}
     * @param httpMethod    {@link HttpMethod}
     */
    public void updateEntityData(EdmEntitySet edmEntitySet,
                                 @SuppressWarnings("unused") List<UriParameter> keyPredicates,
                                 Entity entity,
                                 HttpMethod httpMethod) throws ODataApplicationException {
        var edmEntityType = edmEntitySet.getEntityType();

        // Gather all keys params
        var keys = entity.getProperties().stream()
                .filter(property -> isKey(edmEntityType, property.getName()))
                .map(property -> new AbstractMap.SimpleEntry<>(property.getName(), property.getValue()) {
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Gather all params specified in request
        var params = entity.getProperties()
                .stream()
                .filter(property -> !isKey(edmEntityType, property.getName()))
                .map(property -> new AbstractMap.SimpleEntry<>(property.getName(), property.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (httpMethod.equals(HttpMethod.PUT)) {

            // If method is PUT we have to put to null all other properties which are not specified in request
            edmEntityType.getPropertyNames().stream()
                    .filter(prop -> !keys.containsKey(prop) && !params.containsKey(prop))
                    .forEach(prop -> params.put(prop, null));
        }

        // Update entity
        var rows = mybatisDao.updateEntity(edmEntityType.getName(), keys, params);
        if (rows == 0) {
            throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ROOT);
        }
    }
}
