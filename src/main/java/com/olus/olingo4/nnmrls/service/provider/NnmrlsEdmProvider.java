package com.olus.olingo4.nnmrls.service.provider;

import com.google.common.annotations.VisibleForTesting;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is supposed to declare the metadata of the OData service
 * it is invoked by the Olingo framework e.g. when the metadata document of the service is invoked
 * e.g. <a href="http://localhost:8080/<app>/<service>.svc/$metadata">...</a>
 *
 * @author Oleksii Usatov
 */
public class NnmrlsEdmProvider extends CsdlAbstractEdmProvider {

    // Service Namespace
    public static final String NAMESPACE = "OData.Nnmrls";

    // EDM Container
    public static final String CONTAINER_NAME = "Container";
    public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

    // Entity Types Names
    public static final String ET_LOOKUPS_NAME = "Lookups";
    public static final String ET_MEMBER_DATA_NAME = "MemberData";
    public static final String ET_OFFICE_DATA_NAME = "OfficeData";
    public static final String ET_PROP_DATA_BUISINESS_NAME = "PropertyDataBusiness";
    public static final String ET_PROP_DATA_CHARACT_NAME = "PropertyDataCharacteristics";
    public static final String ET_PROP_DATA_HOA_NAME = "PropertyDataHOA";
    public static final String ET_PROP_DATA_LIST_NAME = "PropertyDataListing";
    public static final String ET_PROP_DATA_LOC_NAME = "PropertyDataLocation";
    public static final String ET_PROP_DATA_STRUCT_NAME = "PropertyDataStructure";
    public static final String ET_PROP_DATA_TAX_NAME = "PropertyDataTax";
    public static final String ET_PROP_DATA_TOUR_NAME = "PropertyDataTour";

    public static final List<String> ALL_ET = List.of(
            ET_LOOKUPS_NAME,
            ET_MEMBER_DATA_NAME,
            ET_OFFICE_DATA_NAME,
            ET_PROP_DATA_BUISINESS_NAME,
            ET_PROP_DATA_CHARACT_NAME,
            ET_PROP_DATA_HOA_NAME,
            ET_PROP_DATA_LIST_NAME,
            ET_PROP_DATA_LOC_NAME,
            ET_PROP_DATA_STRUCT_NAME,
            ET_PROP_DATA_TAX_NAME,
            ET_PROP_DATA_TOUR_NAME
    );

    public static final FullQualifiedName ET_LOOKUPS_FQN = new FullQualifiedName(NAMESPACE, ET_LOOKUPS_NAME);
    public static final FullQualifiedName ET_MEMBER_DATA_FQN = new FullQualifiedName(NAMESPACE, ET_MEMBER_DATA_NAME);
    public static final FullQualifiedName ET_OFFICE_DATA_FQN = new FullQualifiedName(NAMESPACE, ET_OFFICE_DATA_NAME);
    public static final FullQualifiedName ET_PROP_DATA_BUISINESS_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_BUISINESS_NAME);
    public static final FullQualifiedName ET_PROP_DATA_CHARACT_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_CHARACT_NAME);
    public static final FullQualifiedName ET_PROP_DATA_HOA_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_HOA_NAME);
    public static final FullQualifiedName ET_PROP_DATA_LIST_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_LIST_NAME);
    public static final FullQualifiedName ET_PROP_DATA_LOC_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_LOC_NAME);
    public static final FullQualifiedName ET_PROP_DATA_STRUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_STRUCT_NAME);
    public static final FullQualifiedName ET_PROP_DATA_TAX_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_TAX_NAME);
    public static final FullQualifiedName ET_PROP_DATA_TOUR_FQN = new FullQualifiedName(NAMESPACE, ET_PROP_DATA_TOUR_NAME);

    /**
     * All FullQualifiedNames
     */
    public static final List<FullQualifiedName> ALL_FQN = List.of(
            ET_LOOKUPS_FQN,
            ET_MEMBER_DATA_FQN,
            ET_OFFICE_DATA_FQN,
            ET_PROP_DATA_BUISINESS_FQN,
            ET_PROP_DATA_CHARACT_FQN,
            ET_PROP_DATA_HOA_FQN,
            ET_PROP_DATA_LIST_FQN,
            ET_PROP_DATA_LOC_FQN,
            ET_PROP_DATA_STRUCT_FQN,
            ET_PROP_DATA_TAX_FQN,
            ET_PROP_DATA_TOUR_FQN
    );

    // Entity Set Names
    public static final String ES_LOOKUPS_NAME = "Lookups";
    public static final String ES_MEMBER_DATA_NAME = "MemberData";
    public static final String ES_OFFICE_DATA_NAME = "OfficeData";
    public static final String ES_PROP_DATA_BUISINESS_NAME = "PropertyDataBusiness";
    public static final String ES_PROP_DATA_CHARACT_NAME = "PropertyDataCharacteristics";
    public static final String ES_PROP_DATA_HOA_NAME = "PropertyDataHOA";
    public static final String ES_PROP_DATA_LIST_NAME = "PropertyDataListing";
    public static final String ES_PROP_DATA_LOC_NAME = "PropertyDataLocation";
    public static final String ES_PROP_DATA_STRUCT_NAME = "PropertyDataStructure";
    public static final String ES_PROP_DATA_TAX_NAME = "PropertyDataTax";
    public static final String ES_PROP_DATA_TOUR_NAME = "PropertyDataTour";

    public static final List<String> ALL_ES = List.of(
            ES_LOOKUPS_NAME,
            ES_MEMBER_DATA_NAME,
            ES_OFFICE_DATA_NAME,
            ES_PROP_DATA_BUISINESS_NAME,
            ES_PROP_DATA_CHARACT_NAME,
            ES_PROP_DATA_HOA_NAME,
            ES_PROP_DATA_LIST_NAME,
            ES_PROP_DATA_LOC_NAME,
            ES_PROP_DATA_STRUCT_NAME,
            ES_PROP_DATA_TAX_NAME,
            ES_PROP_DATA_TOUR_NAME
    );
    /**
     * Mapping ES to EF
     */
    public static final Map<String, FullQualifiedName> ES_TO_EF = IntStream.range(0, ALL_ES.size())
            .mapToObj(i -> new AbstractMap.SimpleEntry<>(ALL_ES.get(i), ALL_FQN.get(i)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    /**
     * Mapping ES to ET
     */
    public static final Map<String, String> ES_TO_ET = IntStream.range(0, ALL_ES.size())
            .mapToObj(i -> new AbstractMap.SimpleEntry<>(ALL_ES.get(i), ALL_ET.get(i)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    /**
     * Mapping ET to ES
     */
    public static final Map<String, String> ET_TO_ES;

    static {
        ET_TO_ES = IntStream.range(0, ALL_ET.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(ALL_ET.get(i), ALL_ES.get(i)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Cache for CsdlEntityType
     */
    private static final Map<FullQualifiedName, CsdlEntityType> CSDL_ENTITY_TYPE_CACHE = new ConcurrentHashMap<>();

    /**
     * Create {@link CsdlEntityType}
     *
     * @param name name
     * @param type type
     * @return {@link CsdlEntityType}
     */
    @VisibleForTesting
    static CsdlEntitySet createCsdlEntitySet(String name, FullQualifiedName type) {
        CsdlEntitySet entitySet = new CsdlEntitySet();
        entitySet.setName(name);
        entitySet.setType(type);
        return entitySet;
    }

    /**
     * Create Csdl entity type
     *
     * @param entityTypeName name
     * @return {@link CsdlEntityType}
     */
    public static CsdlEntityType getCsdlEntityType(FullQualifiedName entityTypeName) {
        return CSDL_ENTITY_TYPE_CACHE.computeIfAbsent(entityTypeName, (key) -> {
            if (entityTypeName.equals(ET_LOOKUPS_FQN)) {
                return LookupsCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_MEMBER_DATA_FQN)) {
                return MemberDataCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_OFFICE_DATA_FQN)) {
                return OfficeDataCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_BUISINESS_FQN)) {
                return PropertyDataBusinessCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_CHARACT_FQN)) {
                return PropertyDataCharacteristicsCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_HOA_FQN)) {
                return PropertyDataHoaCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_LIST_FQN)) {
                return PropertyDataListingCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_LOC_FQN)) {
                return PropertyDataLocationCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_STRUCT_FQN)) {
                return PropertyDataStructureCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_TAX_FQN)) {
                return PropertyDataTaxCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROP_DATA_TOUR_FQN)) {
                return PropertyDataTourCsdlEntityTypeProvider.createType();
            }
            return null;
        });
    }

    @Override
    public List<CsdlSchema> getSchemas() {

        // create Schema
        var schema = new CsdlSchema();
        schema.setNamespace(NAMESPACE);

        // add EntityTypes
        var entityTypes = new ArrayList<CsdlEntityType>();
        ALL_FQN.forEach(fqn -> entityTypes.add(getEntityType(fqn)));
        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // finally
        var schemas = new ArrayList<CsdlSchema>();
        schemas.add(schema);
        return schemas;
    }

    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {

        // This method is called for one of the EntityTypes that are configured in the Schema
        return getCsdlEntityType(entityTypeName);
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {
        if (entityContainer.equals(CONTAINER)) {
            switch (entitySetName) {
                case ES_LOOKUPS_NAME:
                    return createCsdlEntitySet(ES_LOOKUPS_NAME, ET_LOOKUPS_FQN);
                case ES_MEMBER_DATA_NAME:
                    return createCsdlEntitySet(ES_MEMBER_DATA_NAME, ET_MEMBER_DATA_FQN);
                case ES_OFFICE_DATA_NAME:
                    return createCsdlEntitySet(ES_OFFICE_DATA_NAME, ET_OFFICE_DATA_FQN);
                case ES_PROP_DATA_BUISINESS_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_BUISINESS_NAME, ET_PROP_DATA_BUISINESS_FQN);
                case ES_PROP_DATA_CHARACT_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_CHARACT_NAME, ET_PROP_DATA_CHARACT_FQN);
                case ES_PROP_DATA_HOA_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_HOA_NAME, ET_PROP_DATA_HOA_FQN);
                case ES_PROP_DATA_LIST_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_LIST_NAME, ET_PROP_DATA_LIST_FQN);
                case ES_PROP_DATA_LOC_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_LOC_NAME, ET_PROP_DATA_LOC_FQN);
                case ES_PROP_DATA_STRUCT_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_STRUCT_NAME, ET_PROP_DATA_STRUCT_FQN);
                case ES_PROP_DATA_TAX_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_TAX_NAME, ET_PROP_DATA_TAX_FQN);
                case ES_PROP_DATA_TOUR_NAME:
                    return createCsdlEntitySet(ES_PROP_DATA_TOUR_NAME, ET_PROP_DATA_TOUR_FQN);
            }
        }
        return null;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() {

        // Create EntitySets
        var entitySets = new ArrayList<CsdlEntitySet>();
        ALL_ES.forEach(es -> entitySets.add(getEntitySet(CONTAINER, es)));

        // Create EntityContainer
        var entityContainer = new CsdlEntityContainer();
        entityContainer.setName(CONTAINER_NAME);
        entityContainer.setEntitySets(entitySets);

        return entityContainer;
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

        // This method is invoked when displaying the service document at e.g. http://localhost:8080/<app>/<service>.svc
        if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
            CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
            entityContainerInfo.setContainerName(CONTAINER);
            return entityContainerInfo;
        }
        return null;
    }
}
