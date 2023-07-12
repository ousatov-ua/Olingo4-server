package com.olus.olingo4.nnmrls.util;

import com.google.common.annotations.VisibleForTesting;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Converter of provided DB data to {@link Entity} or {@link EntityCollection}
 *
 * @author Oleksii Usatov
 */
public class Olingo4Converter {

    private Olingo4Converter() {

        // Empty
    }

    /**
     * Converts entity received into map
     *
     * @param entity {@link Entity}
     * @return {@link Map}
     */
    public static Map<String, Object> convertEntity(Entity entity) {
        return entity.getProperties().stream()
                .map(property ->
                        new AbstractMap.SimpleEntry<>(property.getName(), property.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Convert single DB result
     *
     * @param collectionName collection name (e.g. ParagonRawAgents)
     * @param keyName        name of key
     * @param data           DB data
     * @return {@link Entity}
     */
    public static Entity convertMap(String collectionName, String keyName, Map<String, Object> data) {
        final var entity = new Entity();
        data.forEach((key, value) -> entity.addProperty(new Property(null, key, ValueType.PRIMITIVE, value)));
        entity.setId(createId(collectionName, data.get(keyName)));
        return entity;
    }

    /**
     * Convert collection of DB results
     *
     * @param collectionName collection name (e.g. ParagonRawAgents)
     * @param keyName        name of key
     * @param list           collection of DB results
     * @return {@link EntityCollection}
     */
    public static EntityCollection convertListOfMaps(String collectionName, String keyName,
                                                     List<Map<String, Object>> list) {
        var pragentsCollection = new EntityCollection();
        var pragentList = pragentsCollection.getEntities();
        list.forEach(data -> pragentList.add(convertMap(collectionName, keyName, data)));
        return pragentsCollection;
    }

    /**
     * Create URI for specific ID
     *
     * @param entitySetName collection name (e.g. ParagonRawAgents)
     * @param id            ID value
     * @return {@link URI}
     */
    @VisibleForTesting
    static URI createId(String entitySetName, Object id) {
        try {
            if (id instanceof String) {
                return new URI(entitySetName + "('" + id + "')");
            }
            return new URI(entitySetName + "(" + id + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }
}
