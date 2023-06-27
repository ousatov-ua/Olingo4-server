package com.olus.olingo4.nmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingFeaturesMapper;
import com.olus.olingo4.nnmrls.service.provider.ParagonRawListingFeaturesCsdlEntityTypeProvider;
import org.junit.jupiter.api.Test;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRLISTING_FEATURES_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link ParagonRawListingFeaturesCsdlEntityTypeProvider}
 *
 * @author Oleksii Usatov
 */
public class ParagonRawListingFeaturesCsdlEntityTypeProviderTest {

    @Test
    void testCreateType() {

        // Execute
        var type = ParagonRawListingFeaturesCsdlEntityTypeProvider.createType();

        // Verify
        assertEquals(ET_PRLISTING_FEATURES_NAME, type.getName());
        assertEquals("Edm.String", type.getProperty("Mls_Number").getType());
        assertEquals(97, type.getProperties().size());
        assertEquals(1, type.getKey().size());
        assertEquals("Mls_Number", type.getKey().get(0).getName());
    }
}
