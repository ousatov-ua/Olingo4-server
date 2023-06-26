package com.olus.olingo4.nnmrls.dao.mappers;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawListing
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawListingMapper {
    String PK_KEY = "Mls_Number";

    List<Map<String, Object>> selectParagonRawListings(int offset, int limit);

    Map<String, Object> selectParagonRawListingById(String id);
}
