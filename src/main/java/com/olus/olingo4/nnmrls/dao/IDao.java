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
     * Fetch all ParagonRawAgents
     *
     * @return list of agents
     */
    List<Map<String, Object>> getAllParagonRawAgents();

    /**
     * Fetch specific ParagonRawAgent
     *
     * @param id id of entity
     * @return map
     */
    Optional<Map<String, Object>> selectParagonRawAgentById(String id);
}
