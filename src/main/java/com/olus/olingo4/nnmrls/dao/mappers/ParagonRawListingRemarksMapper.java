package com.olus.olingo4.nnmrls.dao.mappers;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawListingRemarks
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawListingRemarksMapper {
    String PK_KEY = "Mls_Number";

    List<Map<String, Object>> selectParagonRawListingRemarks(int offset, int limit);

    Map<String, Object> selectParagonRawListingRemarksById(String id);
}
