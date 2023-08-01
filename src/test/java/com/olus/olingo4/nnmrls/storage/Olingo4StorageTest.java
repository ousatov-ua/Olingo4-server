package com.olus.olingo4.nnmrls.storage;

import com.olus.olingo4.nnmrls.func.FuncDbTest;
import com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider;
import com.olus.olingo4.nnmrls.util.FileUtil;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.core.uri.UriParameterImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Functional unit test for {@link Olingo4Storage}
 *
 * @author Oleksii Usatov
 */
public class Olingo4StorageTest extends FuncDbTest {

    private static Olingo4Storage storage;

    @BeforeAll
    static void setUpAll() {
        storage = new Olingo4Storage();
        Olingo4Storage.enableUpdateEntity = true;
        Olingo4Storage.enableCreateEntity = true;
    }

    @BeforeEach
    void clearData() throws IOException {
        runCommand(FileUtil.getFileContent("sql/clearAll.sql"));
    }

    @Test
    void testIsKey() {

        // Setup
        var entityType = mock(EdmEntityType.class);
        var edmKeyPropertyRef = mock(EdmKeyPropertyRef.class);
        when(edmKeyPropertyRef.getName()).thenReturn("ListingId");
        when(entityType.getKeyPropertyRefs()).thenReturn(List.of(edmKeyPropertyRef));

        // Execute && Verify
        assertTrue(Olingo4Storage.isKey(entityType, "ListingId"));
    }

    @Test
    void testIsKeyNegative() {

        // Setup
        var entityType = mock(EdmEntityType.class);
        var edmKeyPropertyRef = mock(EdmKeyPropertyRef.class);
        when(edmKeyPropertyRef.getName()).thenReturn("id");
        when(entityType.getKeyPropertyRefs()).thenReturn(List.of(edmKeyPropertyRef));

        // Execute && Verify
        assertFalse(Olingo4Storage.isKey(entityType, "ListingId"));
    }

    @Test
    void testGetDataByKeys() {

        // Setup
        runCommandForContent("sql/insertLookups.sql");
        var edmEntitySet = mock(EdmEntitySet.class);
        when(edmEntitySet.getName()).thenReturn(NnmrlsEdmProvider.ES_LOOKUPS_NAME);
        var edmEntityType = mock(EdmEntityType.class);
        when(edmEntityType.getName()).thenReturn(NnmrlsEdmProvider.ET_LOOKUPS_NAME);
        when(edmEntitySet.getEntityType()).thenReturn(edmEntityType);

        var uriParameter = new UriParameterImpl();
        uriParameter.setName("LookupIds");
        uriParameter.setText("1");

        // Execute
        var dataOpt = storage.getDataByKeys(edmEntitySet, List.of(uriParameter),
                List.of("LookupIds", "Lookups", "LookupField"));

        // Verify
        assertTrue(dataOpt.isPresent());
        var data = dataOpt.get();

        assertEquals(3, data.getProperties().size());
        assertEquals(Map.of("Lookups", "Lookups1",
                "LookupIds", 1,
                "LookupField", "LookupField1"), data.getProperties()
                .stream().map(p -> new AbstractMap.SimpleEntry<>(p.getName(), p.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @ParameterizedTest
    @CsvSource({"0,1,1", "1,1,2"})
    void testGetData(int offset, int top, int suffixForFieldValue) {

        // Setup
        runCommandForContent("sql/insertLookups.sql");
        var edmEntitySet = mock(EdmEntitySet.class);
        when(edmEntitySet.getName()).thenReturn(NnmrlsEdmProvider.ES_LOOKUPS_NAME);
        var edmEntityType = mock(EdmEntityType.class);
        when(edmEntityType.getName()).thenReturn(NnmrlsEdmProvider.ET_LOOKUPS_NAME);
        when(edmEntitySet.getEntityType()).thenReturn(edmEntityType);

        var uriParameter = new UriParameterImpl();
        uriParameter.setName("LookupIds");
        uriParameter.setText("1");

        // Execute
        var dataList = storage.getData(edmEntitySet, offset, top,
                List.of("LookupIds", "Lookups", "LookupField"));

        // Verify
        assertEquals(1, dataList.getEntities().size());
        var data = dataList.getEntities().get(0);

        assertEquals(3, data.getProperties().size());
        assertEquals(Map.of("Lookups", "Lookups" + suffixForFieldValue,
                "LookupIds", suffixForFieldValue,
                "LookupField", "LookupField" + suffixForFieldValue), data.getProperties()
                .stream().map(p -> new AbstractMap.SimpleEntry<>(p.getName(), p.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }


    @Test
    void testGetAllData() {

        // Setup
        runCommandForContent("sql/insertLookups.sql");
        var edmEntitySet = mock(EdmEntitySet.class);
        when(edmEntitySet.getName()).thenReturn(NnmrlsEdmProvider.ES_LOOKUPS_NAME);
        var edmEntityType = mock(EdmEntityType.class);
        when(edmEntityType.getName()).thenReturn(NnmrlsEdmProvider.ET_LOOKUPS_NAME);
        when(edmEntitySet.getEntityType()).thenReturn(edmEntityType);

        var uriParameter = new UriParameterImpl();
        uriParameter.setName("LookupIds");
        uriParameter.setText("1");

        // Execute
        var dataList = storage.getData(edmEntitySet, -1, -1,
                List.of("LookupIds", "Lookups", "LookupField"));

        // Verify
        assertEquals(2, dataList.getEntities().size());
        IntStream.range(1, 3)
                .forEach(i -> {
                    var data = dataList.getEntities().get(i - 1);
                    assertEquals(3, data.getProperties().size());
                    assertEquals(Map.of("Lookups", "Lookups" + i,
                            "LookupIds", i,
                            "LookupField", "LookupField" + i), data.getProperties()
                            .stream().map(p -> new AbstractMap.SimpleEntry<>(p.getName(), p.getValue()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
                });
    }

    @ParameterizedTest
    @CsvSource({"UPDATE,POST", "UPDATE,PUT", "UPDATE,PATCH"})
    void testUpdateData(String operation, String method) throws ODataApplicationException {

        // Setup
        var httpMethod = HttpMethod.valueOf(method);

        runCommandForContent("sql/insertLookups.sql");
        var edmEntitySet = mock(EdmEntitySet.class);
        when(edmEntitySet.getName()).thenReturn(NnmrlsEdmProvider.ES_LOOKUPS_NAME);
        var edmEntityType = mock(EdmEntityType.class);
        when(edmEntityType.getName()).thenReturn(NnmrlsEdmProvider.ET_LOOKUPS_NAME);
        when(edmEntityType.getPropertyNames()).thenReturn(List.of("LookupIds", "Lookups", "LookupField"));
        when(edmEntitySet.getEntityType()).thenReturn(edmEntityType);
        var edmKeyPropertyRef = mock(EdmKeyPropertyRef.class);
        when(edmKeyPropertyRef.getName()).thenReturn("LookupIds");
        when(edmEntityType.getKeyPropertyRefs()).thenReturn(List.of(edmKeyPropertyRef));

        var uriParameter = new UriParameterImpl();
        uriParameter.setName("LookupIds");
        uriParameter.setText("1");

        var entity = new Entity();
        var property1 = new Property();
        property1.setName("LookupIds");
        property1.setValue(ValueType.PRIMITIVE, 1);

        entity.addProperty(property1);

        if (httpMethod != HttpMethod.PUT) {
            var property2 = new Property();
            property2.setName("Lookups");
            property2.setValue(ValueType.PRIMITIVE, "Lookups1");
            entity.addProperty(property2);
        }
        var property3 = new Property();
        property3.setName("LookupField");
        property3.setValue(ValueType.PRIMITIVE, "Some Name");
        entity.addProperty(property3);

        // Execute
        switch (operation) {
            case "UPDATE":
                storage.updateEntityData(edmEntitySet, List.of(uriParameter), entity, httpMethod);
                break;
            case "CREATE":
                storage.createEntityData(edmEntitySet, entity);
        }

        // Execute
        var dataOpt = storage.getDataByKeys(edmEntitySet, List.of(uriParameter),
                List.of("LookupIds", "Lookups", "LookupField"));

        // Verify
        assertTrue(dataOpt.isPresent());
        var data = dataOpt.get();
        switch (httpMethod) {
            case POST:
                if ("UPDATE".equals(operation)) {
                    assertEquals("Some Name", data.getProperty("LookupField").getValue());
                    assertEquals("Lookups1", data.getProperty("Lookups").getValue());
                }
                break;
            case PUT:
                if ("UPDATE".equals(operation)) {
                    assertEquals("Some Name", data.getProperty("LookupField").getValue());
                    assertNull(data.getProperty("Lookups"));
                }
                break;
        }
    }

    @Test
    void testCreateData() throws ODataApplicationException {

        // Setup
        var edmEntitySet = mock(EdmEntitySet.class);
        when(edmEntitySet.getName()).thenReturn(NnmrlsEdmProvider.ES_LOOKUPS_NAME);
        var edmEntityType = mock(EdmEntityType.class);
        when(edmEntityType.getName()).thenReturn(NnmrlsEdmProvider.ET_LOOKUPS_NAME);
        when(edmEntityType.getPropertyNames()).thenReturn(List.of("LookupIds", "Lookups", "LookupField"));
        when(edmEntitySet.getEntityType()).thenReturn(edmEntityType);
        var edmKeyPropertyRef = mock(EdmKeyPropertyRef.class);
        when(edmKeyPropertyRef.getName()).thenReturn("LookupIds");
        when(edmEntityType.getKeyPropertyRefs()).thenReturn(List.of(edmKeyPropertyRef));

        var uriParameter = new UriParameterImpl();
        uriParameter.setName("LookupIds");
        uriParameter.setText("1");

        var entity = new Entity();
        var property1 = new Property();
        property1.setName("LookupIds");
        property1.setValue(ValueType.PRIMITIVE, 1);

        entity.addProperty(property1);
        var property2 = new Property();
        property2.setName("Lookups");
        property2.setValue(ValueType.PRIMITIVE, "Lookups1");
        entity.addProperty(property2);
        var property3 = new Property();
        property3.setName("LookupField");
        property3.setValue(ValueType.PRIMITIVE, "Some Name");
        entity.addProperty(property3);

        // Execute
        var result = storage.createEntityData(edmEntitySet, entity);

        // Verify
        var expected = entity.getProperties().stream().map(property ->
                        new AbstractMap.SimpleEntry<>(property.getName(), property.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var received = result.getProperties().stream().map(property ->
                        new AbstractMap.SimpleEntry<>(property.getName(), property.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        assertEquals(expected, received);
    }
}
