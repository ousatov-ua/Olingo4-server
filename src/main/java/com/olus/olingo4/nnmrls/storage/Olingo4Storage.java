package com.olus.olingo4.nnmrls.storage;

import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingFeaturesMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingRemarksMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawOfficeMapper;
import com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider;
import com.olus.olingo4.nnmrls.util.Olingo4Converter;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_PRAGENT_NAME;
import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_PRLISTING_FEATURES_NAME;
import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_PRLISTING_NAME;
import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_PRLISTING_REMARKS_NAME;
import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_PROFFICE_NAME;

/**
 * Storage class to fetch {@link EntityCollection}
 *
 * @author Oleksii Usatov
 */
public class Olingo4Storage {
    private static final boolean TURN_ON_SELECT_LISTINGS = false;
    private static final boolean TURN_ON_SELECT_LISTING_FEATURES = false;

    private static final int LIMIT_OF_TOP_FOR_HEAVY_TABLES = 2;

    private final MyBatisDao mybatisDao;

    public Olingo4Storage() {
        this.mybatisDao = new MyBatisDao();
    }

    private static void validateLimit(int limit) throws ODataApplicationException {
        if (limit > LIMIT_OF_TOP_FOR_HEAVY_TABLES) {
            throw new ODataApplicationException("Not supported 'top' more than " + LIMIT_OF_TOP_FOR_HEAVY_TABLES,
                    HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
        }
        if (limit <= 0) {
            throw new ODataApplicationException("Please specify 'top'! It can have value no more tno more than "
                    + LIMIT_OF_TOP_FOR_HEAVY_TABLES,
                    HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
        }
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
     * Fetch data for specified offset and limit ('skip' and 'top')
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public EntityCollection getData(EdmEntitySet edmEntitySet, int offset, int limit) throws ODataApplicationException {

        var pragentsCollection = new EntityCollection();

        // Check for which EdmEntitySet the data is requested
        if (ES_PRAGENT_NAME.equals(edmEntitySet.getName())) {
            return Olingo4Converter.convertListOfMaps(ES_PRAGENT_NAME,
                    ParagonRawAgentMapper.PK_KEY,
                    mybatisDao.selectAllParagonRawAgents(offset, limit));
        } else if (ES_PROFFICE_NAME.equals(edmEntitySet.getName())) {
            return Olingo4Converter.convertListOfMaps(ES_PROFFICE_NAME,
                    ParagonRawOfficeMapper.PK_KEY,
                    mybatisDao.selectAllParagonRawOffices(offset, limit));
        } else if (ES_PRLISTING_NAME.equals(edmEntitySet.getName())) {
            if (TURN_ON_SELECT_LISTINGS) {
                return Olingo4Converter.convertListOfMaps(ES_PRLISTING_NAME,
                        ParagonRawListingMapper.PK_KEY,
                        mybatisDao.selectAllParagonRawListings(offset, limit));
            }
            throw new ODataApplicationException("Not supported. Please select by key.", HttpStatusCode.NOT_ACCEPTABLE.getStatusCode(), Locale.ROOT);
        } else if (ES_PRLISTING_FEATURES_NAME.equals(edmEntitySet.getName())) {
            if (TURN_ON_SELECT_LISTING_FEATURES) {
                validateLimit(limit);
                return Olingo4Converter.convertListOfMaps(ES_PRLISTING_FEATURES_NAME,
                        ParagonRawListingFeaturesMapper.PK_KEY,
                        mybatisDao.selectAllParagonRawListingFeatures(offset, limit));
            }
            throw new ODataApplicationException("Not supported. Please select by key.", HttpStatusCode.NOT_ACCEPTABLE.getStatusCode(), Locale.ROOT);
        } else if (ES_PRLISTING_REMARKS_NAME.equals(edmEntitySet.getName())) {
            validateLimit(limit);
            return Olingo4Converter.convertListOfMaps(ES_PRLISTING_REMARKS_NAME,
                    ParagonRawListingRemarksMapper.PK_KEY,
                    mybatisDao.selectAllParagonRawListingRemarks(offset, limit));
        }
        return pragentsCollection;
    }

    /**
     * Fetch data for specified offset and limit ('skip' and 'top')
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public Optional<Entity> getDataByKeys(EdmEntitySet edmEntitySet, List<UriParameter> keyParams) throws ODataApplicationException {
        var edmEntityType = edmEntitySet.getEntityType();

        // This is only required if we have more than one Entity Type
        switch (edmEntityType.getName()) {
            case NnmrlsEdmProvider.ET_PRAGENT_NAME:
                for (final UriParameter key : keyParams) {
                    var keyText = getKeyValue(key.getText());

                    // We have a single PK
                    var result = mybatisDao.selectParagonRawAgentById(keyText);
                    if (result.isPresent()) {
                        return Optional.of(Olingo4Converter.convertMap(ES_PRAGENT_NAME,
                                ParagonRawAgentMapper.PK_KEY, result.get()));
                    }
                }
                break;
            case NnmrlsEdmProvider.ET_PROFFICE_NAME:
                for (final UriParameter key : keyParams) {
                    var keyText = getKeyValue(key.getText());

                    // We have a single PK
                    var result = mybatisDao.selectParagonRawOfficeById(keyText);
                    if (result.isPresent()) {
                        return Optional.of(Olingo4Converter.convertMap(ES_PROFFICE_NAME,
                                ParagonRawOfficeMapper.PK_KEY, result.get()));
                    }
                }
                break;
            case NnmrlsEdmProvider.ET_PRLISTING_NAME:
                for (final UriParameter key : keyParams) {
                    var keyText = getKeyValue(key.getText());

                    // We have a single PK
                    var result = mybatisDao.selectParagonRawListingById(keyText);
                    if (result.isPresent()) {
                        return Optional.of(Olingo4Converter.convertMap(ES_PRLISTING_NAME,
                                ParagonRawListingMapper.PK_KEY, result.get()));
                    }
                }
                break;
            case NnmrlsEdmProvider.ET_PRLISTING_FEATURES_NAME:
                for (final UriParameter key : keyParams) {
                    var keyText = getKeyValue(key.getText());

                    // We have a single PK
                    var result = mybatisDao.selectParagonRawListingFeaturesById(keyText);
                    if (result.isPresent()) {
                        return Optional.of(Olingo4Converter.convertMap(ES_PRLISTING_FEATURES_NAME,
                                ParagonRawListingFeaturesMapper.PK_KEY, result.get()));
                    }
                }
                break;
            case NnmrlsEdmProvider.ET_PRLISTING_REMARKS_NAME:
                for (final UriParameter key : keyParams) {
                    var keyText = getKeyValue(key.getText());

                    // We have a single PK
                    var result = mybatisDao.selectParagonRawListingRemarksById(keyText);
                    if (result.isPresent()) {
                        return Optional.of(Olingo4Converter.convertMap(ES_PRLISTING_REMARKS_NAME,
                                ParagonRawListingRemarksMapper.PK_KEY, result.get()));
                    }
                }
                break;
            default:
                return Optional.empty();

        }
        return Optional.empty();
    }

    /**
     * Create entity
     *
     * @param edmEntitySet   {@link EdmEntitySet}
     * @param entityToCreate {@link Entity}
     * @return created {@link Entity}
     */
    public Entity createEntityData(EdmEntitySet edmEntitySet, Entity entityToCreate) throws ODataApplicationException {
        EdmEntityType edmEntityType = edmEntitySet.getEntityType();

        switch (edmEntityType.getName()) {
            case NnmrlsEdmProvider.ET_PRAGENT_NAME:
                return Olingo4Converter.convertMap(ES_PRAGENT_NAME,
                        ParagonRawAgentMapper.PK_KEY,
                        mybatisDao.insertParagonRawAgent(Olingo4Converter.convertEntity(entityToCreate)));
            default:
                throw new ODataApplicationException("Not supported.", HttpStatusCode.NOT_ACCEPTABLE.getStatusCode(), Locale.ROOT);
        }
    }
}
