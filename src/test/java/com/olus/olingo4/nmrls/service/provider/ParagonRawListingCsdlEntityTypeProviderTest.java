package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawOfficeMapper;
import com.olus.olingo4.nnmrls.service.provider.ParagonRawListingCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRLISTING_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link ParagonRawListingCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class ParagonRawListingCsdlEntityTypeProviderTest {
    @Test
    void testCreateType() {

        // Execute
        var type = ParagonRawListingCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PRLISTING_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty(ParagonRawListingMapper.PK_KEY).getType());
        assertEquals(309, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals(ParagonRawOfficeMapper.PK_KEY, type.getKey().get(0).getName());
    }
}
