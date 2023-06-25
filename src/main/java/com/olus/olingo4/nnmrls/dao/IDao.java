package com.olus.olingo4.nnmrls.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IDao {

    List<Map<String, Object>> getAllParagonRawAgents();

    Optional<Map<String, Object>> selectParagonRawAgentById(String id);
}
