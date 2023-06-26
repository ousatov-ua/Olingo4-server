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
     * @param offset offset (aka skip)
     * @param limit  limit (aka top)
     * @return list of agents
     */
    List<Map<String, Object>> selectAllParagonRawAgents(int offset, int limit);

    /**
     * Fetch specific ParagonRawAgent
     *
     * @param id id of entity
     * @return map
     */
    Optional<Map<String, Object>> selectParagonRawAgentById(String id);

    /**
     * Fetch all ParagonRawOffices
     *
     * @param offset offset (aka skip)
     * @param limit  limit (aka top)
     * @return list of offices
     */
    List<Map<String, Object>> selectAllParagonRawOffices(int offset, int limit);

    /**
     * Fetch specific ParagonRawOffice
     *
     * @param id id of entity
     * @return map
     */
    Optional<Map<String, Object>> selectParagonRawOfficeById(String id);

    /**
     * Fetch all ParagonRawListings
     *
     * @param offset offset (aka skip)
     * @param limit  limit (aka top)
     * @return list of listings
     */
    List<Map<String, Object>> selectAllParagonRawListings(int offset, int limit);

    /**
     * Fetch specific ParagonRawListing
     *
     * @param id id of entity
     * @return map
     */
    Optional<Map<String, Object>> selectParagonRawListingById(String id);

    /**
     * Fetch specific ParagonRawListingFeatures
     *
     * @param id id of entity
     * @return map
     */
    Optional<Map<String, Object>> selectParagonRawListingFeaturesById(String id);

    /**
     * Fetch all ParagonRawListingFeatures
     *
     * @param offset offset (aka skip)
     * @param limit  limit (aka top)
     * @return list of features
     */
    List<Map<String, Object>> selectAllParagonRawListingFeatures(int offset, int limit);

    /**
     * Fetch specific ParagonRawListingRemarks
     *
     * @param id id of entity
     * @return map
     */
    Optional<Map<String, Object>> selectParagonRawListingRemarksById(String id);


    /**
     * Fetch all ParagonRawListingRemarks
     *
     * @param offset offset (aka skip)
     * @param limit  limit (aka top)
     * @return list of remarks
     */
    List<Map<String, Object>> selectAllParagonRawListingRemarks(int offset, int limit);
}
