package com.olus.olingo4.nnmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingFeaturesMapper;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRLISTING_FEATURES_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListingFeatures
 *
 * @author Oleksii Usatov
 */
public class ParagonRawListingFeaturesCsdlEntityTypeProvider {

    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!

        //TODO need to request how to map this table because its columns types are JSON
        //TODO I suppose we should only able to fetch some specific column only - but not all columns at once
        var property_0 = new CsdlProperty().setName("Mls_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("LFD_ACCESS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("Agent...").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("LFD_ACCESS_ROAD").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("LFD_ACCESS_TYPE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("LFD_ACCESSIBILITY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("LFD_ADDITIONAL_HOUSING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("LFD_ADJOINS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("LFD_APPLIANCES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("LFD_APPLIANCES_INCL_PSNL_PROP").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("LFD_CEILING_HEIGHT").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("LFD_COMMON_AREA_FACILITIES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("LFD_COMMON_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("LFD_COMMUNITY_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("LFD_CORNERS_MARKED").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("LFD_CROPS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("LFD_CURRENT_USE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("LFD_DEED_RESTRICTIONS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("LFD_DINING_ROOM").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("LFD_DITCHES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("LFD_DOCUMENTS_ON_FILE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("LFD_DOMESTIC_WATER").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_22 = new CsdlProperty().setName("LFD_EASEMENTS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_23 = new CsdlProperty().setName("LFD_ELECTRICITY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_24 = new CsdlProperty().setName("LFD_EXISTING_SEWER_Or_SEPTIC").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_25 = new CsdlProperty().setName("LFD_EXTERIOR").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_26 = new CsdlProperty().setName("LFD_EXTERIOR_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_27 = new CsdlProperty().setName("LFD_FACILITIES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_28 = new CsdlProperty().setName("LFD_FACILITY_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_29 = new CsdlProperty().setName("LFD_FAMILY_ROOM").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_30 = new CsdlProperty().setName("LFD_FARM_Or_RANCH_TYPE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_31 = new CsdlProperty().setName("LFD_FENCED").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_32 = new CsdlProperty().setName("LFD_FENCING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_33 = new CsdlProperty().setName("LFD_FIREPLACE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_34 = new CsdlProperty().setName("LFD_FLOOR_COVERING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_35 = new CsdlProperty().setName("LFD_FLOORING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_36 = new CsdlProperty().setName("LFD_FOUNDATION").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_37 = new CsdlProperty().setName("LFD_GARAGE_Or_CARPORT_TYPE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_38 = new CsdlProperty().setName("LFD_GARAGE_TYPES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_39 = new CsdlProperty().setName("LFD_GRAZING_RIGHTS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_40 = new CsdlProperty().setName("LFD_GREEN_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_41 = new CsdlProperty().setName("LFD_GREEN_FEATURES_SEARCH").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_42 = new CsdlProperty().setName("LFD_HEATING_Or_COOLING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_43 = new CsdlProperty().setName("LFD_HOA_AMENITIES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_44 = new CsdlProperty().setName("LFD_INCL_IN_MONTHLY_FEES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_45 = new CsdlProperty().setName("LFD_INCLUDED_IN_RENT").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_46 = new CsdlProperty().setName("LFD_INSULATION").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_47 = new CsdlProperty().setName("LFD_INT_FTRS_Or_PRSNL_PROP_INCLD").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_48 = new CsdlProperty().setName("LFD_INTERIOR_FIXTURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_49 = new CsdlProperty().setName("LFD_INTERIOR_WALLS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_50 = new CsdlProperty().setName("LFD_IRRIGATION_METHOD").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_51 = new CsdlProperty().setName("LFD_KITCHEN").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_52 = new CsdlProperty().setName("LFD_LANDSCAPED").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_53 = new CsdlProperty().setName("LFD_LAUNDRY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_54 = new CsdlProperty().setName("LFD_LAUNDRY_AREA").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_55 = new CsdlProperty().setName("LFD_LIVESTOCK_CAPACITY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_56 = new CsdlProperty().setName("LFD_LIVING_ROOM").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_57 = new CsdlProperty().setName("LFD_LOADING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_58 = new CsdlProperty().setName("LFD_LOCATION").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_59 = new CsdlProperty().setName("LFD_LOT_IMPROVEMENTS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_60 = new CsdlProperty().setName("LFD_LOT_SIZE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_61 = new CsdlProperty().setName("LFD_MASTER_BEDROOM").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_62 = new CsdlProperty().setName("LFD_OTHER_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_63 = new CsdlProperty().setName("LFD_OTHER_ROOMS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_64 = new CsdlProperty().setName("LFD_OWNERS_MAY_SELL").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_65 = new CsdlProperty().setName("LFD_PARKING_TYPES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_66 = new CsdlProperty().setName("LFD_PATIO_Or_DECK").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_67 = new CsdlProperty().setName("LFD_PERSONAL_PROP_INCLUDED").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_68 = new CsdlProperty().setName("LFD_PERSONAL_PROPERTY_INCL").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_69 = new CsdlProperty().setName("LFD_PRICE_INCLUDES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_70 = new CsdlProperty().setName("LFD_PROPERTY_FENCING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_71 = new CsdlProperty().setName("LFD_PROVEN_MINERALS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_72 = new CsdlProperty().setName("LFD_RESTROOMS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_73 = new CsdlProperty().setName("LFD_ROLL_UP_DOORS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_74 = new CsdlProperty().setName("LFD_ROOF").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_75 = new CsdlProperty().setName("LFD_SMART_FEATURES").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_76 = new CsdlProperty().setName("LFD_SOURCE_FINANCIAL_INFO").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_77 = new CsdlProperty().setName("LFD_SPECIAL_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_78 = new CsdlProperty().setName("LFD_SPRINKLERS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_79 = new CsdlProperty().setName("LFD_STOCK_WATER").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_80 = new CsdlProperty().setName("LFD_SURFACE_WATER").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_81 = new CsdlProperty().setName("LFD_TERM_OF_LEASE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_82 = new CsdlProperty().setName("LFD_TOPOGRAPHY").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_83 = new CsdlProperty().setName("LFD_TYPE_OF_BUSINESS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_84 = new CsdlProperty().setName("LFD_TYPE_OF_LAND").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_85 = new CsdlProperty().setName("LFD_TYPE_OF_LEASE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_86 = new CsdlProperty().setName("LFD_UNIT_FEATURES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_87 = new CsdlProperty().setName("LFD_UTILITIES").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_88 = new CsdlProperty().setName("LFD_UTILITIES_AT_SITE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_89 = new CsdlProperty().setName("LFD_VIEW").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_90 = new CsdlProperty().setName("LFD_WATER_HEATER").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_91 = new CsdlProperty().setName("LFD_WATER_SOURCE").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_92 = new CsdlProperty().setName("LFD_WATER_TEST").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_93 = new CsdlProperty().setName("LFD_WINDOWS").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_94 = new CsdlProperty().setName("LFD_WIRING").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_95 = new CsdlProperty().setName("LFD_ZG___Green_Feature").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_96 = new CsdlProperty().setName("SystemId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0,property_1,property_2,property_3,property_4,property_5,property_6,property_7,property_8,property_9,property_10,property_11,property_12,property_13,property_14,property_15,property_16,property_17,property_18,property_19,property_20,property_21,property_22,property_23,property_24,property_25,property_26,property_27,property_28,property_29,property_30,property_31,property_32,property_33,property_34,property_35,property_36,property_37,property_38,property_39,property_40,property_41,property_42,property_43,property_44,property_45,property_46,property_47,property_48,property_49,property_50,property_51,property_52,property_53,property_54,property_55,property_56,property_57,property_58,property_59,property_60,property_61,property_62,property_63,property_64,property_65,property_66,property_67,property_68,property_69,property_70,property_71,property_72,property_73,property_74,property_75,property_76,property_77,property_78,property_79,property_80,property_81,property_82,property_83,property_84,property_85,property_86,property_87,property_88,property_89,property_90,property_91,property_92,property_93,property_94,property_95,property_96));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName(ParagonRawListingFeaturesMapper.PK_KEY);

        // configure EntityType
        entityType.setName(ET_PRLISTING_FEATURES_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
