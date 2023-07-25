package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_MEMBER_DATA_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListing
 *
 * @author Oleksii Usatov
 */
public class MemberDataCsdlEntityTypeProvider {

    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("MemberKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("JobTitle").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("LastLoginTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("MemberAOR").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("MemberAORMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("MemberAORkey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("MemberAddress1").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("MemberAddress2").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("MemberAssociationComments").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("MemberCarrierRoute").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("MemberCity").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("MemberCountry").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("MemberCountyOrParish").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("MemberDesignation").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("MemberDirectPhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("MemberEmail").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("MemberFax").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("MemberFirstName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("MemberFullName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("MemberHomePhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("MemberIsAssistantTo").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("MemberLanguages").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_22 = new CsdlProperty().setName("MemberLastName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_23 = new CsdlProperty().setName("MemberLoginId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_24 = new CsdlProperty().setName("MemberMiddleName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_25 = new CsdlProperty().setName("MemberMlsAccessYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_26 = new CsdlProperty().setName("MemberMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_27 = new CsdlProperty().setName("MemberMlsSecurityClass").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_28 = new CsdlProperty().setName("MemberMobilePhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_29 = new CsdlProperty().setName("MemberNamePrefix").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_30 = new CsdlProperty().setName("MemberNameSuffix").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_31 = new CsdlProperty().setName("MemberNationalAssociationId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_32 = new CsdlProperty().setName("MemberNickname").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_33 = new CsdlProperty().setName("MemberOfficePhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_34 = new CsdlProperty().setName("MemberOfficePhoneExt").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_35 = new CsdlProperty().setName("MemberOtherPhoneType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_36 = new CsdlProperty().setName("MemberPager").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_37 = new CsdlProperty().setName("MemberPassword").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_38 = new CsdlProperty().setName("MemberPhoneTTYTDD").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_39 = new CsdlProperty().setName("MemberPostalCode").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_40 = new CsdlProperty().setName("MemberPostalCodePlus4").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_41 = new CsdlProperty().setName("MemberPreferredPhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_42 = new CsdlProperty().setName("MemberPreferredPhoneExt").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_43 = new CsdlProperty().setName("MemberStateLicense").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_44 = new CsdlProperty().setName("MemberStateLicenseState").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_45 = new CsdlProperty().setName("MemberStateOrProvince").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_46 = new CsdlProperty().setName("MemberStatus").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_47 = new CsdlProperty().setName("MemberTollFreePhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_48 = new CsdlProperty().setName("MemberType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_49 = new CsdlProperty().setName("MemberVoiceMail").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_50 = new CsdlProperty().setName("MemberVoiceMailExt").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_51 = new CsdlProperty().setName("ModificationTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_52 = new CsdlProperty().setName("OfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_53 = new CsdlProperty().setName("OfficeMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_54 = new CsdlProperty().setName("OfficeName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_55 = new CsdlProperty().setName("OriginalEntryTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_56 = new CsdlProperty().setName("OriginatingSystemID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_57 = new CsdlProperty().setName("OriginatingSystemMemberKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_58 = new CsdlProperty().setName("OriginatingSystemName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_59 = new CsdlProperty().setName("SocialMediaType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_60 = new CsdlProperty().setName("SourceSystemID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_61 = new CsdlProperty().setName("SourceSystemMemberKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_62 = new CsdlProperty().setName("SourceSystemName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_63 = new CsdlProperty().setName("SyndicateTo").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_64 = new CsdlProperty().setName("Office").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_65 = new CsdlProperty().setName("OriginatingSystem").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_66 = new CsdlProperty().setName("SourceSystem").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_67 = new CsdlProperty().setName("MemberOtherPhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_68 = new CsdlProperty().setName("MemberSocialMedia").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_69 = new CsdlProperty().setName("HistoryTransactional").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_70 = new CsdlProperty().setName("Media").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12, property_13, property_14, property_15, property_16, property_17, property_18, property_19, property_20, property_21, property_22, property_23, property_24, property_25, property_26, property_27, property_28, property_29, property_30, property_31, property_32, property_33, property_34, property_35, property_36, property_37, property_38, property_39, property_40, property_41, property_42, property_43, property_44, property_45, property_46, property_47, property_48, property_49, property_50, property_51, property_52, property_53, property_54, property_55, property_56, property_57, property_58, property_59, property_60, property_61, property_62, property_63, property_64, property_65, property_66, property_67, property_68, property_69, property_70));

        // Create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("MemberKey");

        // Configure EntityType
        entityType.setName(ET_MEMBER_DATA_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
