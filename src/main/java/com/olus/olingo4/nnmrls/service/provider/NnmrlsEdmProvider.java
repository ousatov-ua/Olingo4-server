package com.olus.olingo4.nnmrls.service.provider;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public static final String ET_PRAGENT_NAME = "ParagonRawAgent";
    public static final String ET_PROFFICE_NAME = "ParagonRawOffice";
    public static final String ET_PRLISTING_NAME = "ParagonRawListing";
    public static final String ET_PRLISTING_FEATURES_NAME = "ParagonRawListingFeatures";
    public static final String ET_PRLISTING_REMARKS_NAME = "ParagonRawListingRemarks";
    public static final FullQualifiedName ET_PRAGENT_FQN = new FullQualifiedName(NAMESPACE, ET_PRAGENT_NAME);
    public static final FullQualifiedName ET_PROFFICE_FQN = new FullQualifiedName(NAMESPACE, ET_PROFFICE_NAME);
    public static final FullQualifiedName ET_PRLISTING_FQN = new FullQualifiedName(NAMESPACE, ET_PRLISTING_NAME);
    public static final FullQualifiedName ET_PRLISTING_FEATURES_FQN = new FullQualifiedName(NAMESPACE, ET_PRLISTING_FEATURES_NAME);
    public static final FullQualifiedName ET_PRLISTING_REMARKS_FQN = new FullQualifiedName(NAMESPACE, ET_PRLISTING_REMARKS_NAME);

    /**
     * All FullQualifiedNames
     */
    public static final List<FullQualifiedName> ALL_FQN = List.of(
            ET_PRAGENT_FQN,
            ET_PROFFICE_FQN,
            ET_PRLISTING_FQN,
            ET_PRLISTING_FEATURES_FQN,
            ET_PRLISTING_REMARKS_FQN
    );
    // Entity Set Names
    public static final String ES_PRAGENT_NAME = "ParagonRawAgents";
    public static final String ES_PROFFICE_NAME = "ParagonRawOffices";
    public static final String ES_PRLISTING_NAME = "ParagonRawListings";
    public static final String ES_PRLISTING_FEATURES_NAME = "ParagonRawListingFeatures";
    public static final String ES_PRLISTING_REMARKS_NAME = "ParagonRawListingRemarks";

    /**
     * Mapping ES to EF
     */
    public static final Map<String, FullQualifiedName> ES_TO_EF = Map.of(
            ES_PRAGENT_NAME, ET_PRAGENT_FQN,
            ES_PROFFICE_NAME, ET_PROFFICE_FQN,
            ES_PRLISTING_NAME, ET_PRLISTING_FQN,
            ES_PRLISTING_FEATURES_NAME, ET_PRLISTING_FEATURES_FQN,
            ES_PRLISTING_REMARKS_NAME, ET_PRLISTING_REMARKS_FQN
    );

    /**
     * Mapping ES to ET
     */
    public static final Map<String, String> ES_TO_ET = Map.of(
            ES_PRAGENT_NAME, ET_PRAGENT_NAME,
            ES_PROFFICE_NAME, ET_PROFFICE_NAME,
            ES_PRLISTING_NAME, ET_PRLISTING_NAME,
            ES_PRLISTING_FEATURES_NAME, ET_PRLISTING_FEATURES_NAME,
            ES_PRLISTING_REMARKS_NAME, ET_PRLISTING_REMARKS_NAME
    );
    /**
     * Mapping ET to ES
     */
    public static final Map<String, String> ET_TO_ES = Map.of(
            ET_PRAGENT_NAME, ES_PRAGENT_NAME,
            ET_PROFFICE_NAME, ES_PROFFICE_NAME,
            ET_PRLISTING_NAME, ES_PRLISTING_NAME,
            ET_PRLISTING_FEATURES_NAME, ES_PRLISTING_FEATURES_NAME,
            ET_PRLISTING_REMARKS_NAME, ES_PRLISTING_REMARKS_NAME
    );
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
    private static CsdlEntitySet createCsdlEntitySet(String name, FullQualifiedName type) {
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
            if (entityTypeName.equals(ET_PRAGENT_FQN)) {
                return ParagonRawAgentCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PROFFICE_FQN)) {
                return ParagonRawOfficeCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PRLISTING_FQN)) {
                return ParagonRawListingCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PRLISTING_FEATURES_FQN)) {
                return ParagonRawListingFeaturesCsdlEntityTypeProvider.createType();
            }
            if (entityTypeName.equals(ET_PRLISTING_REMARKS_FQN)) {
                return ParagonRawListingRemarksCsdlEntityTypeProvider.createType();
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
        entityTypes.add(getEntityType(ET_PRAGENT_FQN));
        entityTypes.add(getEntityType(ET_PROFFICE_FQN));
        entityTypes.add(getEntityType(ET_PRLISTING_FQN));
        entityTypes.add(getEntityType(ET_PRLISTING_FEATURES_FQN));
        entityTypes.add(getEntityType(ET_PRLISTING_REMARKS_FQN));
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
                case ES_PRAGENT_NAME:
                    return createCsdlEntitySet(ES_PRAGENT_NAME, ET_PRAGENT_FQN);
                case ES_PROFFICE_NAME:
                    return createCsdlEntitySet(ES_PROFFICE_NAME, ET_PROFFICE_FQN);
                case ES_PRLISTING_NAME:
                    return createCsdlEntitySet(ES_PRLISTING_NAME, ET_PRLISTING_FQN);
                case ES_PRLISTING_FEATURES_NAME:
                    return createCsdlEntitySet(ES_PRLISTING_FEATURES_NAME, ET_PRLISTING_FEATURES_FQN);
                case ES_PRLISTING_REMARKS_NAME:
                    return createCsdlEntitySet(ES_PRLISTING_REMARKS_NAME, ET_PRLISTING_REMARKS_FQN);
            }
        }
        return null;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() {

        // Create EntitySets
        var entitySets = new ArrayList<CsdlEntitySet>();
        entitySets.add(getEntitySet(CONTAINER, ES_PRAGENT_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_PROFFICE_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_PRLISTING_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_PRLISTING_FEATURES_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_PRLISTING_REMARKS_NAME));

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
