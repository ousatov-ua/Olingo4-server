package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.service.provider.MemberDataCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_MEMBER_DATA_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link MemberDataCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class MemberDataCsdlEntityTypeProviderTest {
    @Test
    void testCreateType() {

        // Execute
        var type = MemberDataCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_MEMBER_DATA_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty("MemberKey").getType());
        assertEquals(71, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("MemberKey", type.getKey().get(0).getName());
    }
}
