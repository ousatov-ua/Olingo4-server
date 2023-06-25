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

    /**
     * Cache for CsdlEntityType
     */
    private static final Map<FullQualifiedName, CsdlEntityType> CSDL_ENTITY_TYPE_CACHE = new ConcurrentHashMap<>();

    // Service Namespace
    public static final String NAMESPACE = "OData.Nnmrls";

    // EDM Container
    public static final String CONTAINER_NAME = "Container";
    public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

    // Entity Types Names
    public static final String ET_PRAGENT_NAME = "ParagonRawAgent";
    public static final FullQualifiedName ET_PRAGENT_FQN = new FullQualifiedName(NAMESPACE, ET_PRAGENT_NAME);

    // Entity Set Names
    public static final String ES_PRAGENT_NAME = "ParagonRawAgents";


    @Override
    public List<CsdlSchema> getSchemas() {

        // create Schema
        var schema = new CsdlSchema();
        schema.setNamespace(NAMESPACE);

        // add EntityTypes
        var entityTypes = new ArrayList<CsdlEntityType>();
        entityTypes.add(getEntityType(ET_PRAGENT_FQN));
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
        return CSDL_ENTITY_TYPE_CACHE.computeIfAbsent(entityTypeName, (key) -> {
            if (entityTypeName.equals(ET_PRAGENT_FQN)) {
                return ParagonRawAgentCsdlEntityTypeProvider.createType();
            }
            return null;
        });
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {
        if (entityContainer.equals(CONTAINER)) {
            if (entitySetName.equals(ES_PRAGENT_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ES_PRAGENT_NAME);
                entitySet.setType(ET_PRAGENT_FQN);
                return entitySet;
            }
        }
        return null;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() {

        // Create EntitySets
        var entitySets = new ArrayList<CsdlEntitySet>();
        entitySets.add(getEntitySet(CONTAINER, ES_PRAGENT_NAME));

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
