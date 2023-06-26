package com.olus.olingo4.nnmrls.dao.mappers;

import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawListingRemarks
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawListingRemarksMapper {
    String PK_KEY = "Mls_Number";

    List<Map<String, Object>> selectParagonRawListingRemarks(RowBounds rb);

    Map<String, Object> selectParagonRawListingRemarksById(String id);
}
