package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_OFFICE_DATA_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListingFeatures
 *
 * @author Oleksii Usatov
 */
public class OfficeDataCsdlEntityTypeProvider {

    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("OfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("FranchiseAffiliation").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("IDXOfficeParticipationYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("MainOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("MainOfficeMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("ModificationTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("OfficeAOR").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("OfficeAORMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("OfficeAORkey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("OfficeAddress1").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("OfficeAddress2").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("OfficeAssociationComments").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("OfficeBranchType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("OfficeBrokerKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("OfficeBrokerMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("OfficeCity").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("OfficeCorporateLicense").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("OfficeCountyOrParish").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("OfficeEmail").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("OfficeFax").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("OfficeManagerKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("OfficeManagerMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_22 = new CsdlProperty().setName("OfficeMlsId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_23 = new CsdlProperty().setName("OfficeName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_24 = new CsdlProperty().setName("OfficeNationalAssociationId").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_25 = new CsdlProperty().setName("OfficePhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_26 = new CsdlProperty().setName("OfficePhoneExt").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_27 = new CsdlProperty().setName("OfficePostalCode").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_28 = new CsdlProperty().setName("OfficePostalCodePlus4").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_29 = new CsdlProperty().setName("OfficeStateOrProvince").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_30 = new CsdlProperty().setName("OfficeStatus").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_31 = new CsdlProperty().setName("OfficeType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_32 = new CsdlProperty().setName("OriginalEntryTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_33 = new CsdlProperty().setName("OriginatingSystemID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_34 = new CsdlProperty().setName("OriginatingSystemName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_35 = new CsdlProperty().setName("OriginatingSystemOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_36 = new CsdlProperty().setName("SocialMediaType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_37 = new CsdlProperty().setName("SourceSystemID").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_38 = new CsdlProperty().setName("SourceSystemName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_39 = new CsdlProperty().setName("SourceSystemOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_40 = new CsdlProperty().setName("SyndicateAgentOption").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_41 = new CsdlProperty().setName("SyndicateTo").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_42 = new CsdlProperty().setName("MainOffice").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_43 = new CsdlProperty().setName("OfficeBroker").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_44 = new CsdlProperty().setName("OfficeManager").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_45 = new CsdlProperty().setName("OriginatingSystem").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_46 = new CsdlProperty().setName("SourceSystem").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_47 = new CsdlProperty().setName("HistoryTransactional").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_48 = new CsdlProperty().setName("Media").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_49 = new CsdlProperty().setName("OfficeSocialMedia").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12, property_13, property_14, property_15, property_16, property_17, property_18, property_19, property_20, property_21, property_22, property_23, property_24, property_25, property_26, property_27, property_28, property_29, property_30, property_31, property_32, property_33, property_34, property_35, property_36, property_37, property_38, property_39, property_40, property_41, property_42, property_43, property_44, property_45, property_46, property_47, property_48, property_49));

        // create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("OfficeKey");

        // configure EntityType
        entityType.setName(ET_OFFICE_DATA_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
