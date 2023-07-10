package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.service.provider.PropertyDataLocationCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_LOC_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link PropertyDataLocationCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class PropertyDataLocationCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = PropertyDataLocationCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PROP_DATA_LOC_NAME, type.getName());
        assertEquals("Edm.Int32", type.getProperty("ListingId").getType());
        assertEquals(22, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("ListingId", type.getKey().get(0).getName());
    }
}
