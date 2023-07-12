package com.olus.olingo4.nnmrls.service.provider;

import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_CHARACT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link PropertyDataCharacteristicsCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class PropertyDataCharacteristicsCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = PropertyDataCharacteristicsCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PROP_DATA_CHARACT_NAME, type.getName());
        assertEquals("Edm.Int32", type.getProperty("ListingId").getType());
        assertEquals(15, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("ListingId", type.getKey().get(0).getName());
    }
}
