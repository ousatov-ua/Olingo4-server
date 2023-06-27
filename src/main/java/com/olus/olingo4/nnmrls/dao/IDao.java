package com.olus.olingo4.nnmrls.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface of Dao
 *
 * @author Oleksii Usatov
 */
public interface IDao {
    /**
     * Select entity
     *
     * @param tableName Name of table
     * @param offset    offset (aka skip)
     * @param limit     limit (aka top)
     * @return list of {@link Map}
     */
    List<Map<String, Object>> selectAllEntities(String tableName, int offset, int limit);

    /**
     * Select entity
     *
     * @param tableName Name of table
     * @param keyName   key name
     * @param key       key value
     * @return Optional of {@link Map}
     */
    Optional<Map<String, Object>> selectEntity(String tableName, String keyName, String key);

    /**
     * Insert any entity
     *
     * @param tableName Name of table
     * @param data      {@link Map}
     * @return {@link Map}
     */
    Map<String, Object> insertEntity(String tableName, String key, Map<String, Object> data);

    /**
     * Update any entity
     *
     * @param keys - keys
     * @param data - params {@link Map}
     * @return number of rows updated
     */
    int updateEntity(String tableName, Map<String, Object> keys, Map<String, Object> data);
}
