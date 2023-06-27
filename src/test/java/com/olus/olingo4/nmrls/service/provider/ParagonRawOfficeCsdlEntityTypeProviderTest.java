package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawOfficeMapper;
import com.olus.olingo4.nnmrls.service.provider.ParagonRawOfficeCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROFFICE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link ParagonRawOfficeCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class ParagonRawOfficeCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = ParagonRawOfficeCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PROFFICE_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty("Office_Abbreviation").getType());
        assertEquals(42, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("Office_Abbreviation", type.getKey().get(0).getName());
    }
}
