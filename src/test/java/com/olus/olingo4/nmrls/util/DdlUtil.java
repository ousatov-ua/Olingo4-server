package com.olus.olingo4.nmrls.util;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Util class for creation mappings (based on event example or oracle DDL), DDLs for Aurora (based on Oracle DDL),
 * sample GG json messages
 *
 * @author Oleksii Usatov
 */
@Slf4j
public class DdlUtil {

    /**
     * Self descriptive
     */
    private static final Map<String, EdmPrimitiveTypeKind> DB_TYPE_TO_MAPPING_TYPE = Map.of(
            "BLOB", EdmPrimitiveTypeKind.String,
            "INT", EdmPrimitiveTypeKind.Int32,
            "VARCHAR", EdmPrimitiveTypeKind.String,
            "CHAR", EdmPrimitiveTypeKind.String,
            "FLOAT", EdmPrimitiveTypeKind.Int16,
            "BOOLEAN", EdmPrimitiveTypeKind.Boolean,
            "DATE", EdmPrimitiveTypeKind.Date,
            "DATETIME", EdmPrimitiveTypeKind.DateTimeOffset);

    public static void main(String... args) {
        createMappings();
    }

    @SuppressWarnings("unused")
    private static void createMappings() {

        Set.of("ParagonRawAgent.sql")
                .forEach(dbSqlFile -> {
                    try {
                        generateSourceCode(getFileContent(dbSqlFile));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * Generate JAVA code for Olingo4
     * @param ddl ddl code
     * @throws JSQLParserException exception
     * @throws IOException exception
     */
    private static void generateSourceCode(String ddl) throws JSQLParserException, IOException {
        var statement = CCJSqlParserUtil.parse(ddl);
        var createTable = (CreateTable) statement;

        String property = "property_";
        var number = new AtomicInteger(0);
        try (var fileWriter = new FileWriter("java_code.java", true)) {
            var properties = new LinkedList<String>();
            createTable.getColumnDefinitions().forEach(columnDefinition -> {
                        String columnName = columnDefinition.getColumnName();
                        var dataType = columnDefinition.getColDataType().getDataType();
                        var currentProperty = property + number;
                        properties.add(currentProperty);
                        number.getAndIncrement();
                        try {
                            fileWriter.write(MessageFormat.format(
                                    "var {0} = new CsdlProperty().setName(\"{1}\").setType(EdmPrimitiveTypeKind.{2}.getFullQualifiedName());\n",
                                    currentProperty, columnName, DB_TYPE_TO_MAPPING_TYPE.get(dataType.toUpperCase())));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            fileWriter.write("var entityType = new CsdlEntityType();\n");
            fileWriter.write(MessageFormat.format("entityType.setProperties(Arrays.asList({0}));\n",
                    String.join(",", properties)));
            fileWriter.write("\n");
        }
    }

    /**
     * Load content for specified file path
     *
     * @param filePath file path
     * @return content
     * @throws IOException exception
     */
    private static String getFileContent(String filePath) throws IOException {
        return Resources.toString(Resources.getResource(filePath), StandardCharsets.UTF_8).trim();
    }
}
