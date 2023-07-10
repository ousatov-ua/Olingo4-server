package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.service.provider.OfficeDataCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_OFFICE_DATA_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link OfficeDataCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class OfficeDataCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = OfficeDataCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_OFFICE_DATA_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty("OfficeKey").getType());
        assertEquals(50, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("OfficeKey", type.getKey().get(0).getName());
    }
}
