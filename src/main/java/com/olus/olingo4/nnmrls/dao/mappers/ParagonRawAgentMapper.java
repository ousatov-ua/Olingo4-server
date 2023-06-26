package com.olus.olingo4.nnmrls.dao.mappers;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawAgent
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawAgentMapper {
    String PK_KEY = "User_Code";

    List<Map<String, Object>> selectParagonRawAgents(int offset, int limit);

    Map<String, Object> selectParagonRawAgentById(String id);

    void insertParagonRawAgent(Map<String, Object> data);
}
