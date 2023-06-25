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

    List<Map<String, Object>> selectParagonRawAgentsLimited();

    List<Map<String, Object>> selectParagonRawAgents(RowBounds rb);

    Map<String, Object> selectParagonRawAgentById(String id);
}
