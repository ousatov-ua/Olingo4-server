package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_CHARACT_NAME;

public class PropertyDataCharacteristicsCsdlEntityTypeProvider {

    public static CsdlEntityType createType() {

        var property_0 = new CsdlProperty().setName("ListingId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("LandLeaseYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("LeaseTerm").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("LotSizeArea").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("LotSizeUnits").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("NumberOfUnitsTotal").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("UnitNumber").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("ElementarySchool").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("MiddleOrJuniorSchool").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("HighSchool").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("Horses").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("HUDOwned").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("TaxAnnualAmount").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("Directions").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("Zoning").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12, property_13, property_14));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("ListingId");

        // configure EntityType
        entityType.setName(ET_PROP_DATA_CHARACT_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
