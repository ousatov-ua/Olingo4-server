package com.olus.olingo4.nnmrls.service.provider;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PRAGENT_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawAgent
 *
 * @author Oleksii Usatov
 */
public class ParagonRawAgentCsdlEntityTypeProvider {
    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("User_Code").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("Active").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("Goomzee").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("NRDS_ID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("Agent_Url").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("Agent_Zip").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("Team_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("Agent_City").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("Agent_Type").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("Date_Added").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("Agent_Email").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("Agent_State").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("Status_Date").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("Team_E_mail").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("Update_Date").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("Agent_Status").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("Photographer").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("Board_User_ID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("Agent_Address2").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("Agent_Mail_Zip").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("Multiple_Logon").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("Security_Level").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_22 = new CsdlProperty().setName("Agent_Last_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_23 = new CsdlProperty().setName("Agent_Mail_City").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_24 = new CsdlProperty().setName("Agent_Office_ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_25 = new CsdlProperty().setName("Update_By_Agent").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_26 = new CsdlProperty().setName("Agent_Birth_Date").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_27 = new CsdlProperty().setName("Agent_First_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_28 = new CsdlProperty().setName("Agent_Identifier").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_29 = new CsdlProperty().setName("Agent_License_ID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_30 = new CsdlProperty().setName("Agent_Logon_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_31 = new CsdlProperty().setName("Agent_Mail_State").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_32 = new CsdlProperty().setName("Agent_Salutation").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_33 = new CsdlProperty().setName("MLStimate_Active").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_34 = new CsdlProperty().setName("User_Photos_Code").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_35 = new CsdlProperty().setName("Agent_Designation").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_36 = new CsdlProperty().setName("Agent_License_Date").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_37 = new CsdlProperty().setName("Agent_Mail_Address2").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_38 = new CsdlProperty().setName("Agent_Phone1_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_39 = new CsdlProperty().setName("Agent_Phone2_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_40 = new CsdlProperty().setName("Agent_Phone3_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_41 = new CsdlProperty().setName("Agent_Phone4_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_42 = new CsdlProperty().setName("Agent_Phone5_Number").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_43 = new CsdlProperty().setName("Primary_Association").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_44 = new CsdlProperty().setName("Agent_Middle_Initial").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_45 = new CsdlProperty().setName("Agent_Mail_Preference").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_46 = new CsdlProperty().setName("Agent_Mail_Street_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_47 = new CsdlProperty().setName("Agent_Phone1_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_48 = new CsdlProperty().setName("Agent_Phone1_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_49 = new CsdlProperty().setName("Agent_Phone2_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_50 = new CsdlProperty().setName("Agent_Phone2_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_51 = new CsdlProperty().setName("Agent_Phone3_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_52 = new CsdlProperty().setName("Agent_Phone3_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_53 = new CsdlProperty().setName("Agent_Phone4_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_54 = new CsdlProperty().setName("Agent_Phone4_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_55 = new CsdlProperty().setName("Agent_Phone5_CountryId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_56 = new CsdlProperty().setName("Agent_Phone5_Extension").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_57 = new CsdlProperty().setName("UPLogo_image_Timestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_58 = new CsdlProperty().setName("UPAgent_image_Timestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_59 = new CsdlProperty().setName("Agent_Phone1_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_60 = new CsdlProperty().setName("Agent_Phone2_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_61 = new CsdlProperty().setName("Agent_Phone3_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_62 = new CsdlProperty().setName("Agent_Phone4_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_63 = new CsdlProperty().setName("Agent_Phone5_Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_64 = new CsdlProperty().setName("State_Agent_Is_Member_Of").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_65 = new CsdlProperty().setName("Agent_Address_Street_Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4,
                property_5, property_6, property_7, property_8, property_9, property_10, property_11,
                property_12, property_13, property_14, property_15, property_16, property_17, property_18,
                property_19, property_20, property_21, property_22, property_23, property_24, property_25,
                property_26, property_27, property_28, property_29, property_30, property_31, property_32,
                property_33, property_34, property_35, property_36, property_37, property_38, property_39,
                property_40, property_41, property_42, property_43, property_44, property_45, property_46,
                property_47, property_48, property_49, property_50, property_51, property_52, property_53,
                property_54, property_55, property_56, property_57, property_58, property_59, property_60,
                property_61, property_62, property_63, property_64, property_65));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName(ParagonRawAgentMapper.PK_KEY);

        // configure EntityType
        entityType.setName(ET_PRAGENT_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
