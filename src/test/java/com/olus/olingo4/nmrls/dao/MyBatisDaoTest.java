package com.olus.olingo4.nmrls.dao;

import com.olus.olingo4.nmrls.func.FuncDbTest;
import com.olus.olingo4.nmrls.util.FileUtil;
import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Functional test for {@link com.olus.olingo4.nnmrls.dao.MyBatisDao}
 *
 * @author Oleksii Usatov
 */
@Slf4j
class MyBatisDaoTest extends FuncDbTest {

    private static MyBatisDao myBatisDao;

    @BeforeAll
    static void setUpAll() {
        myBatisDao = new MyBatisDao();
    }

    @BeforeEach
    void clearData() throws IOException {
        runCommand(FileUtil.getFileContent("sql/clearAll.sql"));
    }

    // Lookups start
    @Test
    void testSelectLookupsById() {

        // Setup
        runCommandForContent("sql/insertLookups.sql");

        // Execute
        var resultOpt = myBatisDao.selectEntity("Lookups", "LookupIds", 1);

        // Verify
        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        assertEquals(1, result.get("LookupIds"));
        assertEquals("Lookups1", result.get("Lookups"));
        assertEquals("StandardName1", result.get("StandardName"));
    }

    @Test
    void testSelectLookups() {

        // Setup
        runCommandForContent("sql/insertLookups.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("Lookups", -1, -1);

        // Verify
        assertEquals(2, result.size());
        var item1 = result.get(0);
        assertEquals(1, item1.get("LookupIds"));
        assertEquals("Lookups1", item1.get("Lookups"));
        assertEquals("StandardName1", item1.get("StandardName"));

        var item2 = result.get(1);
        assertEquals(2, item2.get("LookupIds"));
        assertEquals("Lookups2", item2.get("Lookups"));
        assertEquals("StandardName2", item2.get("StandardName"));
    }

    @Test
    void testSelectParagonRawAgentDataWithRowBounds() {

        // Setup
        runCommandForContent("sql/insertLookups.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("Lookups", 1, 1);

        // Verify
        assertEquals(1, result.size());
        var item1 = result.get(0);
        assertEquals(2, item1.get("LookupIds"));
        assertEquals("Lookups2", item1.get("Lookups"));
        assertEquals("StandardName2", item1.get("StandardName"));
    }

    @Test
    void testInsertLookups() {

        // Setup
        var request = Map.<String, Object>of(
                "LookupIds", 1,
                "Lookups", "Lookups1",
                "StandardName", "StandardName1"
        );

        // Execute
        var item = myBatisDao.insertEntity("Lookups", "LookupIds", request);

        // Verify
        assertEquals(1, item.get("LookupIds"));
        assertEquals("Lookups1", item.get("Lookups"));
        assertEquals("StandardName1", item.get("StandardName"));

        var resultOpt = myBatisDao.selectEntity("Lookups", "LookupIds", 1);
        assertTrue(resultOpt.isPresent());
        item = resultOpt.get();

        // Verify
        assertEquals(1, item.get("LookupIds"));
        assertEquals("Lookups1", item.get("Lookups"));
        assertEquals("StandardName1", item.get("StandardName"));
    }

    @Test
    void testUpdateLookups() {

        // Setup
        var request = Map.<String, Object>of(
                "LookupIds", 1,
                "Lookups", "Lookups1",
                "StandardName", "StandardName1"
        );

        var item = myBatisDao.insertEntity("Lookups", "LookupIds", request);

        request = Map.of(
                "LookupIds", 1,
                "Lookups", "Lookups1new",
                "StandardName", "StandardName1new"
        );

        // Execute
        var rows = myBatisDao.updateEntity("Lookups", Map.of("LookupIds", 1), request);
        assertEquals(1, rows);

        // Verify
        var resultOpt = myBatisDao.selectEntity("Lookups", "LookupIds", 1);
        assertTrue(resultOpt.isPresent());
        item = resultOpt.get();

        // Verify
        assertEquals(1, item.get("LookupIds"));
        assertEquals("Lookups1new", item.get("Lookups"));
        assertEquals("StandardName1new", item.get("StandardName"));
    }

    @Test
    void testSelectLookupsWithRowBoundsAndColumns() {

        // Setup
        runCommandForContent("sql/insertLookups.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("Lookups", 1, 1,
                List.of("LookupIds", "Lookups"));

        // Verify
        var item = result.get(0);
        assertEquals(2, item.get("LookupIds"));
        assertEquals("Lookups2", item.get("Lookups"));
        assertEquals(2, item.size());
    }

    // Lookups stop
}
