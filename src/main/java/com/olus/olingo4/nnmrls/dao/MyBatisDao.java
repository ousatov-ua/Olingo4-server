package com.olus.olingo4.nnmrls.dao;

import com.olus.olingo4.nnmrls.dao.mappers.Mapper;
import com.olus.olingo4.nnmrls.exception.DaoException;
import com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.*;

/**
 * Implementation for {@link IDao}
 *
 * @author Oleksii Usatov
 */
@Slf4j
public class MyBatisDao implements IDao {
    private static final int DEFAULT_ROW_LIMIT = 30;
    private final SqlSessionFactory sqlSessionFactory;
    private final Map<String, Collection<SqlColumn<Object>>> tableToAllColumns = new HashMap<>();

    /**
     * Constructor
     */
    public MyBatisDao() {
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(getResource());
        NnmrlsEdmProvider.ALL_FQN.forEach(fqn -> {
            var table = SqlTable.of(fqn.getName());
            var columns = NnmrlsEdmProvider.getCsdlEntityType(fqn).getProperties()
                    .stream()
                    .map(prop -> {
                        var name = columnName(prop.getName());
                        return SqlColumn.of(name, table);
                    })
                    .collect(Collectors.toList());
            tableToAllColumns.put(fqn.getName(), columns);
        });
    }

    private static int offset(int offset) {
        return Math.max(offset, 0);
    }

    private static int limit(int limit) {
        if (limit <= 0) {
            return DEFAULT_ROW_LIMIT;
        }
        return limit;
    }

    /**
     * Get mapper (MyBatis)
     *
     * @param tableName tableName
     * @param session   current {@link SqlSession}
     * @return {@link Mapper}
     */
    private static Mapper getMapper(String tableName, SqlSession session) {
        switch (tableName) {
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
                return session.getMapper(Mapper.class);

        }
        return null;
    }

    /**
     * We need to add '`' if column contains dots
     *
     * @param columnName column name
     * @return formatted column name
     */
    private static String columnName(String columnName) {
        return columnName.contains(".") ? "`" + columnName + "`" : columnName;
    }

    /**
     * Convert list of string columns to type {@link SqlColumn}
     *
     * @param table       {@link SqlTable}
     * @param columnNames list of columns
     * @return array of {@link SqlColumn}
     */
    private SqlColumn<?>[] getSqlColumns(SqlTable table, List<String> columnNames) {
        if (columnNames.isEmpty()) {
            return tableToAllColumns.get(table.tableNameAtRuntime()).toArray(new SqlColumn[0]);
        } else {
            return columnNames.stream()
                    .map(col -> SqlColumn.of(col, table))
                    .toArray(SqlColumn[]::new);
        }
    }

    /**
     * Get mybatis resource, created for test purpose to override it
     *
     * @return {@link InputStream}
     */
    protected InputStream getResource() {
        String resource = "mybatis/mybatis-config.xml";
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllEntities(String tableName, int offset, int limit) {
        return selectAllEntities(tableName, offset, limit, Collections.emptyList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllEntities(String tableName, int offset, int limit,
                                                       List<String> columnNames) {
        final var table = SqlTable.of(tableName);
        try (var session = sqlSessionFactory.openSession()) {
            var mapper = getMapper(tableName, session);
            if (mapper != null) {
                var columns = getSqlColumns(table, columnNames);
                var select = SqlBuilder.select(columns)
                        .from(table)
                        .limit(limit(limit))
                        .offset(offset(offset))
                        .build()
                        .render(RenderingStrategies.MYBATIS3);
                return mapper.selectAllEntities(select);
            }
        } catch (Exception e) {
            log.error("Could not select objects!", e);
            throw new DaoException("Could not select objects!");
        }
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectEntity(String tableName, String keyName, Object key) {
        return selectEntity(tableName, keyName, key, Collections.emptyList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectEntity(String tableName, String keyName, Object key,
                                                      List<String> columnNames) {
        final var table = SqlTable.of(tableName);
        keyName = columnName(keyName);
        try (var session = sqlSessionFactory.openSession()) {
            var mapper = getMapper(tableName, session);
            if (mapper != null) {
                var columns = getSqlColumns(table, columnNames);
                var select = SqlBuilder.select(columns)
                        .from(table)
                        .where(SqlColumn.of(keyName, table), SqlBuilder.isEqualTo(key))
                        .build()
                        .render(RenderingStrategies.MYBATIS3);
                return Optional.of(mapper.selectEntity(select));
            }
        } catch (Exception e) {
            log.error("Could not select object!", e);
            throw new DaoException("Could not select object!");
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> insertEntity(String tableName, String keyName, Map<String, Object> data) {
        final var table = SqlTable.of(tableName);
        try (var session = sqlSessionFactory.openSession(true)) {
            var mapper = getMapper(tableName, session);
            if (mapper != null) {
                var sqlBuilder = SqlBuilder.insert(data);
                var insert = sqlBuilder.into(table);
                for (var param : data.entrySet()) {
                    var paramName = columnName(param.getKey());
                    insert = insert.map(SqlColumn.of(paramName, table)).toProperty(param.getKey());
                }
                mapper.insertEntity(insert.build().render(RenderingStrategies.MYBATIS3));
            }
        } catch (Exception e) {
            log.error("Could not insert object!", e);
            throw new DaoException("Could not insert object!");
        }
        var opt = selectEntity(tableName, keyName, data.get(keyName), Collections.emptyList());
        return opt.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateEntity(String tableName, Map<String, Object> keys, Map<String, Object> data) {
        final var table = SqlTable.of(tableName);
        try (var session = sqlSessionFactory.openSession(true)) {
            var sqlBuilder = SqlBuilder.update(table);
            for (var param : data.entrySet()) {
                var paramName = columnName(param.getKey());
                sqlBuilder = sqlBuilder.set(SqlColumn.of(paramName, table)).equalTo(param.getValue());
            }
            UpdateDSL<UpdateModel>.UpdateWhereBuilder updateBuilder = null;
            for (var key : keys.entrySet()) {
                var keyName = columnName(key.getKey());
                updateBuilder = sqlBuilder.where(SqlColumn.of(keyName, table), SqlBuilder.isEqualTo(key.getValue()));
            }
            if (updateBuilder != null) {
                var finalUpdate = updateBuilder.build()
                        .render(RenderingStrategies.MYBATIS3);
                var mapper = getMapper(tableName, session);
                if (mapper != null) {
                    return mapper.updateEntity(finalUpdate);
                }
            }
        } catch (Exception e) {
            log.error("Could not update object!", e);
            throw new DaoException("Could not update object!");
        }
        return 0;
    }
}
