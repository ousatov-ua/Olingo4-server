package com.olus.olingo4.nnmrls.dao.mappers;

import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawListing
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawListingFeaturesMapper {
    String PK_KEY = "Mls_Number";

    Map<String, Object> selectParagonRawListingFeaturesById(String id);

    List<Map<String, Object>> selectParagonRawListingFeatures(RowBounds rb);
}
