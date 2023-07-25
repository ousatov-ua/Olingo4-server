package com.olus.olingo4.nnmrls.service.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.CONTAINER;
import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_TO_EF;
import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_LOOKUPS_FQN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NnmrlsEdmProviderTest {

    private static final NnmrlsEdmProvider NNMRLS_EDM_PROVIDER = new NnmrlsEdmProvider();

    @Test
    void testCreateCsdlEntitySet() {
        var entitySet = NnmrlsEdmProvider.createCsdlEntitySet("name", ET_LOOKUPS_FQN);
        assertEquals("name", entitySet.getName());
        assertEquals(ET_LOOKUPS_FQN.getFullQualifiedNameAsString(), entitySet.getType());
    }

    @ParameterizedTest
    @CsvSource({"Lookups", "MemberData", "OfficeData", "PropertyDataBusiness", "PropertyDataCharacteristics",
            "PropertyDataHOA", "PropertyDataListing", "PropertyDataLocation", "PropertyDataStructure", "PropertyDataTax",
            "PropertyDataTour"})
    void testGetEntitySet(String es) {
        var set = NNMRLS_EDM_PROVIDER.getEntitySet(CONTAINER, es);
        assertEquals(es, set.getName());
        assertEquals(ES_TO_EF.get(es).getFullQualifiedNameAsString(), set.getType());
    }
}
