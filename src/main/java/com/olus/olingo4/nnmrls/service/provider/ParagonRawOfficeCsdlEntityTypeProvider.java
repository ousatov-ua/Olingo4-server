package com.olus.olingo4.nnmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawOfficeMapper;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROFFICE_NAME;

public class ParagonRawOfficeCsdlEntityTypeProvider {

    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("Office_Abbreviation").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("Active...").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("Board_ID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("Date_Added").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("MLS_Member").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("Office_Url").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("Office_Zip").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("IDX_Include").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("Office_City").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("Office_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("Office_Type").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("Update_Date").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("Board_Member").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("Office_Email").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("Office_State").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("Main_Office_ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("Office_Contact").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("Point2_Enabled").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("Office_Address2").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("Office_Mail_Zip").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("Office_Mail_City").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("Office_Identifier").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_22 = new CsdlProperty().setName("Office_Mail_State").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_23 = new CsdlProperty().setName("Designated_Broker_ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_24 = new CsdlProperty().setName("Office_Mail_Address2").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_25 = new CsdlProperty().setName("Office_Phone1_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_26 = new CsdlProperty().setName("Office_Phone2_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_27 = new CsdlProperty().setName("Office_Phone3_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_28 = new CsdlProperty().setName("Office_Computer_Access").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_29 = new CsdlProperty().setName("Office_Mail_Street_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_30 = new CsdlProperty().setName("Office_Phone1_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_31 = new CsdlProperty().setName("Office_Phone1_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_32 = new CsdlProperty().setName("Office_Phone2_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_33 = new CsdlProperty().setName("Office_Phone2_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_34 = new CsdlProperty().setName("Office_Phone3_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_35 = new CsdlProperty().setName("Office_Phone3_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_36 = new CsdlProperty().setName("Homesnap_Showings_Opt_In").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_37 = new CsdlProperty().setName("Offce_Address_Street_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_38 = new CsdlProperty().setName("Office_Phone1_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_39 = new CsdlProperty().setName("Office_Phone2_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_40 = new CsdlProperty().setName("Office_Phone3_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_41 = new CsdlProperty().setName("National_RDS_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4,
                property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12,
                property_13, property_14, property_15, property_16, property_17, property_18, property_19, property_20,
                property_21, property_22, property_23, property_24, property_25, property_26, property_27,
                property_28, property_29, property_30, property_31, property_32, property_33, property_34, property_35,
                property_36, property_37, property_38, property_39, property_40, property_41));


        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName(ParagonRawOfficeMapper.PK_KEY);

        // configure EntityType
        entityType.setName(ET_PROFFICE_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
