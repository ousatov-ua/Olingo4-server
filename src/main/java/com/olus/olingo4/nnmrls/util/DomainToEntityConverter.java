package com.olus.olingo4.nnmrls.util;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Converter of provided DB data to {@link Entity} or {@link EntityCollection}
 *
 * @author Oleksii Usatov
 */
public class DomainToEntityConverter {

    private DomainToEntityConverter() {

        // Empty
    }

    /**
     * Convert single DB result
     *
     * @param collectionName collection name (e.g. ParagonRawAgents)
     * @param keyName        name of key
     * @param data           DB data
     * @return {@link Entity}
     */
    public static Entity convertEntity(String collectionName, String keyName, Map<String, Object> data) {
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
    public static EntityCollection convertEntityList(String collectionName, String keyName,
                                                     List<Map<String, Object>> list) {
        var pragentsCollection = new EntityCollection();
        var pragentList = pragentsCollection.getEntities();
        list.forEach(data -> pragentList.add(convertEntity(collectionName, keyName, data)));
        return pragentsCollection;
    }

    /**
     * Create URI for specific ID
     *
     * @param entitySetName collection name (e.g. ParagonRawAgents)
     * @param id            ID value
     * @return {@link URI}
     */
    private static URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + id + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }
}
