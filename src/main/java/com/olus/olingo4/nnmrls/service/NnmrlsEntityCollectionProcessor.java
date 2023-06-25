package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import com.olus.olingo4.nnmrls.util.DomainToEntityConverter;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * This class is invoked by the Olingo framework when the the OData service is invoked order to display a list/collection of data (entities).
 * This is the case if an EntitySet is requested by the user.
 * Such an example URL would be:
 * <a href="http://localhost:8080/ExampleService1/ExampleService1.svc/ParagonRawAgents">...</a>
 *
 * @author Oleksii Usatov
 */
public class NnmrlsEntityCollectionProcessor implements EntityCollectionProcessor {

    private OData odata;
    private ServiceMetadata serviceMetadata;
    private MyBatisDao mybatisDao;

    // Our processor is initialized with the OData context object
    public void init(OData odata, ServiceMetadata serviceMetadata) {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
        this.mybatisDao = new MyBatisDao();
    }


    /**
     * The only method that is declared in the EntityCollectionProcessor interface
     * this method is called, when the user fires a request to an EntitySet
     * in our example, the URL would be:
     * <a href="http://localhost:8080/ExampleService1/ExampleServlet1.svc/ParagonRawAgents">...</a>
     **/
    public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType responseFormat) throws SerializerException {

        // 1st we have retrieve the requested EntitySet from the uriInfo object (representation of the parsed service URI)
        List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
        UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in our example, the first segment is the EntitySet
        EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();

        // 2nd: fetch the data from backend for this requested EntitySetName // it has to be delivered as EntitySet object
        EntityCollection entitySet = getData(edmEntitySet);

        // 3rd: create a serializer based on the requested format (json)
        ODataSerializer serializer = odata.createSerializer(responseFormat);

        // 4th: Now serialize the content: transform from the EntitySet object to InputStream
        EdmEntityType edmEntityType = edmEntitySet.getEntityType();
        ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).build();

        final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
        EntityCollectionSerializerOptions opts =
                EntityCollectionSerializerOptions.with().id(id).contextURL(contextUrl).build();
        SerializerResult serializedContent = serializer.entityCollection(serviceMetadata, edmEntityType, entitySet, opts);

        // Finally: configure the response object: set the body, headers and status code
        response.setContent(serializedContent.getContent());
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    }


    /**
     * Helper method for providing some sample data
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    private EntityCollection getData(EdmEntitySet edmEntitySet) {

        EntityCollection pragentsCollection = new EntityCollection();
        // check for which EdmEntitySet the data is requested
        if (NnmrlsEdmProvider.ES_PRAGENT_NAME.equals(edmEntitySet.getName())) {
            return DomainToEntityConverter.convertParagonRawAgentList(mybatisDao.getAllParagonRawAgents());
        }

        return pragentsCollection;
    }

    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + id + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }
}
