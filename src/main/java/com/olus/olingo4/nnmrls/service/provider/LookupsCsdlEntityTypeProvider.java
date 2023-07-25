package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_LOOKUPS_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawAgent
 *
 * @author Oleksii Usatov
 */
public class LookupsCsdlEntityTypeProvider {
    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("LookupIds").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("Lookups").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("StandardName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2));

        // Create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("LookupIds");

        // Configure EntityType
        entityType.setName(ET_LOOKUPS_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
