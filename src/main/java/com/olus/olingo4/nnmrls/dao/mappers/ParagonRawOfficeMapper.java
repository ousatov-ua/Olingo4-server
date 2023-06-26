package com.olus.olingo4.nnmrls.dao.mappers;

import java.util.List;
import java.util.Map;

/**
 * Mapper for MyBatis for ParagonRawOffice
 *
 * @author Oleksii Usatov
 */
public interface ParagonRawOfficeMapper {
    String PK_KEY = "Office_Abbreviation";

    List<Map<String, Object>> selectParagonRawOffices(int offset, int limit);

    Map<String, Object> selectParagonRawOfficeById(String id);
}
