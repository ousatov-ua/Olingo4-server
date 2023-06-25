package com.olus.olingo4.nnmrls.mybatis.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Custom type handler for {@link LocalDateTime}
 * It is needed to be in sync with Olingo4
 *
 * @author Oleksii Usatov
 */
@MappedTypes(LocalDateTime.class)
public class CustomLocalDateTimeHandler extends BaseTypeHandler<Timestamp> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Timestamp parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public Timestamp getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName, Timestamp.class);
    }

    @Override
    public Timestamp getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getObject(columnIndex, Timestamp.class);
    }

    @Override
    public Timestamp getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getObject(columnIndex, Timestamp.class);
    }
}

