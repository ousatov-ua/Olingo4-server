package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRLISTING_REMARKS_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListingReamarks
 *
 * @author Oleksii Usatov
 */
public class ParagonRawListingRemarksCsdlEntityTypeProvider {
    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("Mls_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("Extended_Public_Remarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("Private_Remarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("Public_Remarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("Syndication_Remarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("SystemId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("Mls_Number");

        // configure EntityType
        entityType.setName(ET_PRLISTING_REMARKS_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
