package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import com.olus.olingo4.nnmrls.service.provider.ParagonRawAgentCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRAGENT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link ParagonRawAgentCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
class ParagonRawAgentCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = ParagonRawAgentCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PRAGENT_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty(ParagonRawAgentMapper.PK_KEY).getType());
        assertEquals(66, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals(ParagonRawAgentMapper.PK_KEY, type.getKey().get(0).getName());
    }
}
