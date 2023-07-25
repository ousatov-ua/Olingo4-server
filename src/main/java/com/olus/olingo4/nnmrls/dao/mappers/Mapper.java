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

    int updateEntity(UpdateStatementProvider updateStatement);

    void insertEntity(InsertStatementProvider<?> insertStatement);

    Map<String, Object> selectEntity(SelectStatementProvider selectStatement);

    List<Map<String, Object>> selectAllEntities(SelectStatementProvider selectStatement);
}
