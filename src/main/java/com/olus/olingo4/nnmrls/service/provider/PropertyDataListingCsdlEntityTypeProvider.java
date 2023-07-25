package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import java.util.Arrays;
import java.util.Collections;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ET_PROP_DATA_LIST_NAME;

/**
 * Class for generation {@link CsdlEntityType} for ParagonRawListingReamarks
 *
 * @author Oleksii Usatov
 */
public class PropertyDataListingCsdlEntityTypeProvider {
    public static CsdlEntityType createType() {

        // Create EntityType properties - Please use DdlUtil to generate code !!!!
        var property_0 = new CsdlProperty().setName("ListingId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_1 = new CsdlProperty().setName("ListingAgreement").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_2 = new CsdlProperty().setName("ListingService").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_3 = new CsdlProperty().setName("MlsStatus").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_4 = new CsdlProperty().setName("OriginatingSystemKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_5 = new CsdlProperty().setName("OriginatingSystemName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_6 = new CsdlProperty().setName("SourceSystemKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_7 = new CsdlProperty().setName("SourceSystemName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_8 = new CsdlProperty().setName("StandardStatus").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_9 = new CsdlProperty().setName("BuyerAgencyCompensation").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_10 = new CsdlProperty().setName("BuyerAgencyCompensationType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_11 = new CsdlProperty().setName("DualVariableCompensationYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_12 = new CsdlProperty().setName("CancellationDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_13 = new CsdlProperty().setName("CloseDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_14 = new CsdlProperty().setName("ContingentDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_15 = new CsdlProperty().setName("ContractStatusChangeDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_16 = new CsdlProperty().setName("ExpirationDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_17 = new CsdlProperty().setName("ListingContractDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_18 = new CsdlProperty().setName("ModificationTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_19 = new CsdlProperty().setName("OffMarketDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_20 = new CsdlProperty().setName("OnMarketDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_21 = new CsdlProperty().setName("OriginalEntryTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_22 = new CsdlProperty().setName("PurchaseContractDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_23 = new CsdlProperty().setName("StatusChangeTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_24 = new CsdlProperty().setName("WithdrawnDate").setType(EdmPrimitiveTypeKind.Date.getFullQualifiedName());
        var property_25 = new CsdlProperty().setName("InternetAddressDisplayYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_26 = new CsdlProperty().setName("InternetAutomatedValuationDisplayYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_27 = new CsdlProperty().setName("InternetConsumerCommentYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_28 = new CsdlProperty().setName("InternetEntireListingDisplayYN").setType(EdmPrimitiveTypeKind.Boolean.getFullQualifiedName());
        var property_29 = new CsdlProperty().setName("PhotosChangeTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_30 = new CsdlProperty().setName("PhotosCount").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_31 = new CsdlProperty().setName("ClosePrice").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_32 = new CsdlProperty().setName("ListPrice").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_33 = new CsdlProperty().setName("OriginalListPrice").setType(EdmPrimitiveTypeKind.Double.getFullQualifiedName());
        var property_34 = new CsdlProperty().setName("PrivateRemarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_35 = new CsdlProperty().setName("PublicRemarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_36 = new CsdlProperty().setName("SyndicationRemarks").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_37 = new CsdlProperty().setName("ShowingContactPhone").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_38 = new CsdlProperty().setName("ShowingContactPhoneExt").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_39 = new CsdlProperty().setName("ShowingInstructions").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_40 = new CsdlProperty().setName("BuyerAgentKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_41 = new CsdlProperty().setName("CoBuyerAgentKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_42 = new CsdlProperty().setName("ListAgentKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_43 = new CsdlProperty().setName("CoListAgentKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_44 = new CsdlProperty().setName("BuyerOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_45 = new CsdlProperty().setName("CoBuyerOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_46 = new CsdlProperty().setName("ListOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_47 = new CsdlProperty().setName("CoListOfficeKey").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_48 = new CsdlProperty().setName("PropertyType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_49 = new CsdlProperty().setName("PropertySubType").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_50 = new CsdlProperty().setName("DaysOnMarket").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_51 = new CsdlProperty().setName("CumulativeDaysOnMarket").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        var property_52 = new CsdlProperty().setName("DocumentsChangeTimestamp").setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());
        var property_53 = new CsdlProperty().setName("Possession").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var property_54 = new CsdlProperty().setName("SpecialListingConditions").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
        var entityType = new CsdlEntityType();
        entityType.setProperties(Arrays.asList(property_0, property_1, property_2, property_3, property_4, property_5, property_6, property_7, property_8, property_9, property_10, property_11, property_12, property_13, property_14, property_15, property_16, property_17, property_18, property_19, property_20, property_21, property_22, property_23, property_24, property_25, property_26, property_27, property_28, property_29, property_30, property_31, property_32, property_33, property_34, property_35, property_36, property_37, property_38, property_39, property_40, property_41, property_42, property_43, property_44, property_45, property_46, property_47, property_48, property_49, property_50, property_51, property_52, property_53, property_54));

        // Create CsdlPropertyRef for Key element
        var propertyRef = new CsdlPropertyRef();
        propertyRef.setName("ListingId");

        // Configure EntityType
        entityType.setName(ET_PROP_DATA_LIST_NAME);
        entityType.setKey(Collections.singletonList(propertyRef));
        return entityType;
    }
}
