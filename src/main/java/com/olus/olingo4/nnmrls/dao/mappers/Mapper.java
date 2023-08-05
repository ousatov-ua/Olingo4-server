package com.olus.olingo4.nnmrls.dao.mappers;

import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis
 *
 * @author Oleksii Usatov
 */
public interface Mapper {

    /**
     * Update data
     *
     * @param updateStatement statement
     * @return number of rows updated
     */
    int updateEntity(UpdateStatementProvider updateStatement);

    /**
     * Insert data
     *
     * @param insertStatement insert statement
     */
    void insertEntity(InsertStatementProvider<?> insertStatement);

    /**
     * Select single entity
     *
     * @param selectStatement select statement
     * @return map
     */
    Map<String, Object> selectEntity(SelectStatementProvider selectStatement);

    /**
     * Select all entities
     *
     * @param selectStatement select statement
     * @return List of {@link Map}
     */
    List<Map<String, Object>> selectAllEntities(SelectStatementProvider selectStatement);
}
