package com.olus.olingo4.nnmrls.util;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link Olingo4Converter}
 *
 * @author Oleksii Usatov
 */
class Olingo4ConverterTest {

    @Test
    void testCreateId() {

        // Verify
        assertEquals("Entity(1)", Olingo4Converter.createId("Entity", 1).toString());
        assertEquals("Entity('1')", Olingo4Converter.createId("Entity", "1").toString());
    }

    @Test
    void convertEntity() {

        // Setup
        Entity entity = new Entity();

        var property1 = new Property();
        property1.setName("prop1");
        property1.setValue(ValueType.PRIMITIVE, "1");

        var property2 = new Property();
        property2.setName("prop2");
        property2.setValue(ValueType.PRIMITIVE, "2");

        entity.getProperties().add(property1);
        entity.getProperties().add(property2);

        // Execute
        var result = Olingo4Converter.convertEntity(entity);

        // Verify
        assertEquals(Map.of("prop1", "1",
                "prop2", "2"), result);
    }
}
