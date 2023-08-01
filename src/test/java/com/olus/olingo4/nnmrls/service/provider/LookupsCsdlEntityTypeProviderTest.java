package com.olus.olingo4.nnmrls.service.provider;

import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_LOOKUPS_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link LookupsCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
class LookupsCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = LookupsCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_LOOKUPS_NAME, type.getName());
        assertEquals("Edm.Int32", type.getProperty("LookupIds").getType());
        assertEquals(4, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("LookupIds", type.getKey().get(0).getName());
    }
}
