package com.olus.olingo4.nnmrls.dao.mappers;

import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawListing
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawListingMapper {
    String PK_KEY = "Mls_Number";

    List<Map<String, Object>> selectParagonRawListings(RowBounds rb);

    Map<String, Object> selectParagonRawListingById(String id);
}
