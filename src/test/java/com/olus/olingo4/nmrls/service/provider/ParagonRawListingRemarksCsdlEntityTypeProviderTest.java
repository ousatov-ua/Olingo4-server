package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingRemarksMapper;
import com.olus.olingo4.nnmrls.service.provider.ParagonRawListingRemarksCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRLISTING_REMARKS_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link ParagonRawListingRemarksCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class ParagonRawListingRemarksCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = ParagonRawListingRemarksCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PRLISTING_REMARKS_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty(ParagonRawListingRemarksMapper.PK_KEY).getType());
        assertEquals(6, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals(ParagonRawListingRemarksMapper.PK_KEY, type.getKey().get(0).getName());
    }
}
