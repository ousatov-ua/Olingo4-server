package com.olus.olingo4.nnmrls.service.provider;

import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_LIST_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link PropertyDataListingCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class PropertyDataListingCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = PropertyDataListingCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PROP_DATA_LIST_NAME, type.getName());
        assertEquals("Edm.Int32", type.getProperty("ListingId").getType());
        assertEquals("Edm.Date", type.getProperty("CancellationDate").getType());
        assertEquals("Edm.DateTimeOffset", type.getProperty("ModificationTimestamp").getType());
        assertEquals(55, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("ListingId", type.getKey().get(0).getName());
    }
}
