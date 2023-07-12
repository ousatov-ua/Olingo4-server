package com.olus.olingo4.nnmrls.service.provider;

import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_TOUR_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link PropertyDataTourCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class PropertyDataTourCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = PropertyDataTourCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PROP_DATA_TOUR_NAME, type.getName());
        assertEquals("Edm.Int32", type.getProperty("ListingId").getType());
        assertEquals(2, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("ListingId", type.getKey().get(0).getName());
    }
}
