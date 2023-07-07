package com.olus.olingo4.nmrls.dao;

import com.olus.olingo4.nmrls.func.FuncDbTest;
import com.olus.olingo4.nmrls.util.FileUtil;
import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    // ParagonRawAgent start
    @Test
    void testSelectParagonRawAgentDataById() {

        // Setup
        runCommandForContent("sql/insertParagonRawAgent.sql");

        // Execute
        var resultOpt = myBatisDao.selectEntity("ParagonRawAgent", "User_Code", "code1");

        // Verify
        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        assertEquals("code1", result.get("User_Code"));
        assertEquals("A", result.get("Active"));
        assertEquals(2, result.get("Agent_Office_ID"));
        assertEquals("2003-05-02", result.get("Agent_License_Date").toString());
        assertEquals("Hello text 1", new String((byte[]) result.get("Agent_Phone5_CountryId")));
    }

    @Test
    void testSelectParagonRawAgentData() {

        // Setup
        runCommandForContent("sql/insertParagonRawAgent.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawAgent", -1, -1);

        // Verify
        assertEquals(2, result.size());
        var agent1 = result.get(0);
        assertEquals("code1", agent1.get("User_Code"));
        assertEquals("A", agent1.get("Active"));
        assertEquals(2, agent1.get("Agent_Office_ID"));
        assertEquals("2003-05-02", agent1.get("Agent_License_Date").toString());
        assertEquals("Hello text 1", new String((byte[]) agent1.get("Agent_Phone5_CountryId")));

        var agent2 = result.get(1);
        assertEquals("code2", agent2.get("User_Code"));
        assertEquals("B", agent2.get("Active"));
        assertEquals(3, agent2.get("Agent_Office_ID"));
        assertEquals("2003-05-03", agent2.get("Agent_License_Date").toString());
        assertEquals("Hello text 2", new String((byte[]) agent2.get("Agent_Phone5_CountryId")));
    }

    @Test
    void testSelectParagonRawAgentDataWithRowBounds() {

        // Setup
        runCommandForContent("sql/insertParagonRawAgent.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawAgent", 1, 1);

        // Verify
        assertEquals(1, result.size());
        var agent1 = result.get(0);
        assertEquals("code2", agent1.get("User_Code"));
        assertEquals("B", agent1.get("Active"));
        assertEquals(3, agent1.get("Agent_Office_ID"));
        assertEquals("2003-05-03", agent1.get("Agent_License_Date").toString());
        assertEquals("Hello text 2", new String((byte[]) agent1.get("Agent_Phone5_CountryId")));
    }

    @Test
    void testInsertParagonRawAgent() {

        // Setup
        var request = Map.of(
                "User_Code", "Test",
                "Active", "A",
                "Goomzee", "B",
                "UPLogo_image_Timestamp", (Object) Timestamp.valueOf("2017-02-23 01:02:03")
        );

        // Execute
        var agent = myBatisDao.insertEntity("ParagonRawAgent", "User_Code", request);

        // Verify
        assertEquals("Test", agent.get("User_Code"));
        assertEquals("A", agent.get("Active"));
        assertEquals("B", agent.get("Goomzee"));
        assertEquals("2017-02-23 01:02:03.0", agent.get("UPLogo_image_Timestamp").toString());

        var agentOpt = myBatisDao.selectEntity("ParagonRawAgent", "User_Code", "Test");
        assertTrue(agentOpt.isPresent());
        agent = agentOpt.get();

        // Verify
        assertEquals("Test", agent.get("User_Code"));
        assertEquals("A", agent.get("Active"));
        assertEquals("B", agent.get("Goomzee"));
        assertEquals("2017-02-23 01:02:03.0", agent.get("UPLogo_image_Timestamp").toString());
    }

    // ParagonRawAgent stop

    // ParagonRawOffice start
    @Test
    void testSelectParagonRawOfficeDataById() {

        // Setup
        runCommandForContent("sql/insertParagonRawOffice.sql");

        // Execute
        var resultOpt = myBatisDao.selectEntity("ParagonRawOffice", "Office_Abbreviation", "code1");

        // Verify
        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        assertEquals("code1", result.get("Office_Abbreviation"));

        // TODO Active... = {={={=true}}}
        // assertEquals(true, result.get("Active"));
        assertEquals("2003-05-02 01:02:03.0", result.get("Date_Added").toString());
        assertEquals(3, result.get("Office_Identifier"));
    }

    @Test
    void testSelectParagonRawOfficeData() {

        // Setup
        runCommandForContent("sql/insertParagonRawOffice.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawOffice", -1, -1);

        // Verify
        assertEquals(2, result.size());
        var office1 = result.get(0);
        assertEquals("code1", office1.get("Office_Abbreviation"));

        // TODO Active... = {={={=true}}}
        // assertEquals(true, office1.get("Active"));
        assertEquals("2003-05-02 01:02:03.0", office1.get("Date_Added").toString());
        assertEquals(3, office1.get("Office_Identifier"));

        var office2 = result.get(1);
        assertEquals("code2", office2.get("Office_Abbreviation"));

        // TODO Active... = {={={=false}}}
        // assertEquals(false, office2.get("Active"));
        assertEquals("2003-05-03 02:03:04.0", office2.get("Date_Added").toString());
        assertEquals(4, office2.get("Office_Identifier"));
    }

    @Test
    void testSelectParagonRawOfficeDataWithRowBounds() {

        // Setup
        runCommandForContent("sql/insertParagonRawOffice.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawOffice", 1, 1);

        // Verify
        assertEquals(1, result.size());
        var office2 = result.get(0);
        assertEquals("code2", office2.get("Office_Abbreviation"));

        // TODO Active... = {={={=false}}}
        // assertEquals(false, office2.get("Active"));
        assertEquals("2003-05-03 02:03:04.0", office2.get("Date_Added").toString());
        assertEquals(4, office2.get("Office_Identifier"));
    }

    // ParagonRawOffice end


    // ParagonRawListing start
    @Test
    void testSelectParagonRawListingDataById() {

        // Setup
        runCommandForContent("sql/insertParagonRawListing.sql");

        // Execute
        var resultOpt = myBatisDao.selectEntity("ParagonRawListing", "Mls_Number", "code1");

        // Verify
        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        // Verify
        assertEquals("code1", result.get("Mls_Number"));
        assertEquals(1, result.get("_1_Bedroom___Number__Baths"));
        assertEquals(1.25f, result.get("Acres_of_Water_Rights"));

        assertEquals("2003-05-03", result.get("Date_Available").toString());
        assertEquals("2004-06-07 02:03:04.0", result.get("Doc_Timestamp").toString());
    }

    @Test
    void testSelectParagonRawListingDataByIdWithColumns() {

        // Setup
        runCommandForContent("sql/insertParagonRawListing.sql");

        // Execute
        var resultOpt = myBatisDao.selectEntity("ParagonRawListing", "Mls_Number", "code1",
                List.of("Acres_of_Water_Rights", "Mls_Number"));

        // Verify
        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        // Verify
        assertEquals("code1", result.get("Mls_Number"));
        assertNull(result.get("_1_Bedroom___Number__Baths"));
        assertEquals(1.25f, result.get("Acres_of_Water_Rights"));

        assertNull(result.get("Date_Available"));
        assertNull(result.get("Doc_Timestamp"));
    }

    @Test
    void testSelectParagonRawListingData() {

        // Setup
        runCommandForContent("sql/insertParagonRawListing.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawListing", -1, -1);

        // Verify
        assertEquals(2, result.size());
        var listing1 = result.get(0);
        assertEquals("code1", listing1.get("Mls_Number"));
        assertEquals(1, listing1.get("_1_Bedroom___Number__Baths"));
        assertEquals(1.25f, listing1.get("Acres_of_Water_Rights"));

        assertEquals("2003-05-03", listing1.get("Date_Available").toString());
        assertEquals("2004-06-07 02:03:04.0", listing1.get("Doc_Timestamp").toString());

        var listing2 = result.get(1);
        assertEquals("code2", listing2.get("Mls_Number"));
        assertEquals(2, listing2.get("_1_Bedroom___Number__Baths"));
        assertEquals(2.25f, listing2.get("Acres_of_Water_Rights"));

        assertEquals("2003-06-04", listing2.get("Date_Available").toString());
        assertEquals("2004-07-08 03:04:05.0", listing2.get("Doc_Timestamp").toString());
    }

    @Test
    void testSelectParagonRawListingDataWithRowBounds() {

        // Setup
        runCommandForContent("sql/insertParagonRawListing.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawListing", 1, 1);

        // Verify
        var listing1 = result.get(0);
        assertEquals("code2", listing1.get("Mls_Number"));
        assertEquals(2, listing1.get("_1_Bedroom___Number__Baths"));
        assertEquals(2.25f, listing1.get("Acres_of_Water_Rights"));

        assertEquals("2003-06-04", listing1.get("Date_Available").toString());
        assertEquals("2004-07-08 03:04:05.0", listing1.get("Doc_Timestamp").toString());
    }

    // ParagonRawListing stop

    // ParagonRawListingRemarks start
    @Test
    void testSelectParagonRawListingRemarksDataById() {

        // Setup
        runCommandForContent("sql/insertParagonRawListingRemarks.sql");

        // Execute
        var resultOpt = myBatisDao.selectEntity("ParagonRawListingRemarks", "Mls_Number", "code1");

        // Verify
        assertTrue(resultOpt.isPresent());
        var result = resultOpt.get();
        assertEquals("code1", result.get("Mls_Number"));
        assertEquals("public remarks 1", result.get("Extended_Public_Remarks"));
        assertEquals(1L, result.get("SystemId"));
    }

    @Test
    void testSelectParagonRawListingRemarksData() {

        // Setup
        runCommandForContent("sql/insertParagonRawListingRemarks.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawListingRemarks", -1, -1);

        // Verify
        assertEquals(2, result.size());
        var listing1 = result.get(0);
        assertEquals("code1", listing1.get("Mls_Number"));
        assertEquals("public remarks 1", listing1.get("Extended_Public_Remarks"));
        assertEquals(1L, listing1.get("SystemId"));

        var listing2 = result.get(1);
        assertEquals("code2", listing2.get("Mls_Number"));
        assertEquals("public remarks 2", listing2.get("Extended_Public_Remarks"));
        assertEquals(2L, listing2.get("SystemId"));
    }

    @Test
    void testSelectParagonRawListingDataRemarksWithRowBounds() {

        // Setup
        runCommandForContent("sql/insertParagonRawListingRemarks.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawListingRemarks", 1, 1);

        // Verify
        var listing1 = result.get(0);
        assertEquals("code2", listing1.get("Mls_Number"));
        assertEquals("public remarks 2", listing1.get("Extended_Public_Remarks"));
        assertEquals(2L, listing1.get("SystemId"));
    }

    @Test
    void testSelectParagonRawListingDataRemarksWithRowBoundsAndColumns() {

        // Setup
        runCommandForContent("sql/insertParagonRawListingRemarks.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawListingRemarks", 1, 1,
                List.of("Mls_Number", "Extended_Public_Remarks"));

        // Verify
        var listing1 = result.get(0);
        assertEquals("code2", listing1.get("Mls_Number"));
        assertEquals("public remarks 2", listing1.get("Extended_Public_Remarks"));
        assertEquals(2, listing1.size());
    }

    // ParagonRawListingRemarks end

    // ParagonRawListingFeatures start
    @Test
    void testSelectParagonRawListingFeaturesData() {

        // Setup
        runCommandForContent("sql/insertParagonRawListingFeatures.sql");

        // Execute
        var result = myBatisDao.selectAllEntities("ParagonRawListingFeatures", -1, -1,
                List.of("Mls_Number", "LFD_ACCESS"));

        // Verify
        assertEquals(2, result.size());
        var listing1 = result.get(0);
        assertEquals("code1", listing1.get("Mls_Number"));
        assertEquals("{\"Access\": \"Public\"}", listing1.get("LFD_ACCESS"));

        var listing2 = result.get(1);
        assertEquals("code2", listing2.get("Mls_Number"));
        assertEquals("{\"Access\": \"Private\"}", listing2.get("LFD_ACCESS"));

        assertEquals(2, listing1.size());
        assertEquals(2, listing2.size());
    }

    // ParagonRawListingFeatures end
}
