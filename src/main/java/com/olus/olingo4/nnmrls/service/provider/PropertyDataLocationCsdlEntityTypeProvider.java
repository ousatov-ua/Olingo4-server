package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_LOC_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListingReamarks
 *
 * @author Oleksii Usatov
 */
public class PropertyDataLocationCsdlEntityTypeProvider {
    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("ListingId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("City").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("Country").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("CountyOrParish").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("PostalCity").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("PostalCode").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("PostalCodePlus4").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("StateOrProvince").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("StreetAddtionalInfo").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("StreetDirPrefix").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("StreetDirSuffix").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("StreetName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("StreetNumber").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("StreetNumberNumeric").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("StreetSuffix").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("StreetSuffixModifier").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("Township").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("UnitNumber").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("UnparsedAddress").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("MLSAreaMajor").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("Lattitude").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("Longitude").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12, property_13, property_14, property_15, property_16, property_17, property_18, property_19, property_20, property_21));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("ListingId");

        // configure EntityType
        entityType.setName(ET_PROP_DATA_LOC_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
