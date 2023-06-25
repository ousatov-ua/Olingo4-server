package com.olus.olingo4.nnmrls.dao.mappers;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawAgent
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawAgentMapper {

    List<Map<String, Object>> selectParagonRawAgents();

    Map<String, Object> selectParagonRawAgentById(String id);
}
