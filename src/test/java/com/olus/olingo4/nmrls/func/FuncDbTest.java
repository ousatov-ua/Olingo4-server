package com.olus.olingo4.nmrls.func;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import com.olus.olingo4.nmrls.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.List;

/**
 * Abstract class for functional testing.
 * It will start Embedded DB, apply all DDLs.
 *
 * @author Oleksii Usatov
 */
@Slf4j
public abstract class FuncDbTest {
    private static final int DB_PORT = 49152;
    private static final List<String> DDLS = List.of(
            "ddl/Lookups.sql",
            "ddl/MemberData.sql",
            "ddl/OfficeData.sql",
            "ddl/PropertyDataBuisiness.sql",
            "ddl/PropertyDataCharacteristics.sql",
            "ddl/PropertyDataHOA.sql",
            "ddl/PropertyDataListing.sql",
            "ddl/PropertyDataLocation.sql",
            "ddl/PropertyDataStructure.sql",
            "ddl/PropertyDataTax.sql",
            "ddl/PropertyDataTour.sql"
    );

    private static DB db;

    /**
     * Run command for content (reference to file is passed)
     *
     * @param reference reference to file
     */
    protected static void runCommandForContent(String reference) {
        try {
            runCommand(FileUtil.getFileContent(reference));
        } catch (Exception e) {
            log.error("Cannot run command for content", e);
        }
    }

    /**
     * Run command on embedded DB
     *
     * @param command command (sql)
     */
    protected static void runCommand(String command) {
        try {
            db.run(command);
        } catch (ManagedProcessException e) {
            throw new RuntimeException("Cannot run command!", e);
        }
    }

    /**
     * First, start our DB
     *
     * @throws ManagedProcessException exception
     */
    @BeforeAll
    static void setUpDb() throws ManagedProcessException {
        db = DB.newEmbeddedDB(DB_PORT);
        db.start();
        DDLS.forEach(sql -> {
            try {
                db.run("use test;" + FileUtil.getFileContent(sql));
            } catch (ManagedProcessException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Started embedded MariaDB");
    }

    /**
     * Stop our DB
     *
     * @throws ManagedProcessException exception
     */
    @AfterAll
    static void tearDownDb() throws ManagedProcessException {
        db.stop();
        log.info("Stopped embedded MariaDb");
    }
}
