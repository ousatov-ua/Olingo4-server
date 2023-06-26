package com.olus.olingo4.nnmrls.dao.mappers;

import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawAgent
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawAgentMapper {
    String PK_KEY = "User_Code";

    List<Map<String, Object>> selectParagonRawAgents(RowBounds rb);

    Map<String, Object> selectParagonRawAgentById(String id);

    void insertParagonRawAgent(Map<String, Object> data);
}
