package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_STRUCT_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListingReamarks
 *
 * @author Oleksii Usatov
 */
public class PropertyDataStructureCsdlEntityTypeProvider {
    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("ListingId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("BathroomsFull").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("BathroomsPartial").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("BathroomsTotalInteger").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("BedroomsTotal").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("GarageSpaces").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("GarageYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("Levels").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("LivingArea").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("LivingAreaUnits").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("Make").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("MobileLength").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("MobileWidth").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("Model").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("PropertyAttachedYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("Stories").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("StoriesTotal").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("YearBuilt").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12, property_13, property_14, property_15, property_16, property_17));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("ListingId");

        // configure EntityType
        entityType.setName(ET_PROP_DATA_STRUCT_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
