package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;

/**
 * This class is invoked by the Olingo framework when the the OData service is invoked order to display a list/collection of data (entities).
 * This is the case if an EntitySet is requested by the user.
 * Such an example URL would be:
 * <a href="http://localhost:8080/ExampleService1/ExampleService1.svc/ParagonRawAgents">...</a>
 *
 * @author Oleksii Usatov
 */
public class NnmrlsEntityCollectionProcessor implements EntityCollectionProcessor {

    private final Olingo4Storage storage;
    private OData odata;
    private ServiceMetadata serviceMetadata;

    public NnmrlsEntityCollectionProcessor(Olingo4Storage storage) {
        this.storage = storage;
    }

    // Our processor is initialized with the OData context object
    public void init(OData odata, ServiceMetadata serviceMetadata) {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
    }

    /**
     * The only method that is declared in the EntityCollectionProcessor interface
     * this method is called, when the user fires a request to an EntitySet
     * in our example, the URL would be:
     * <a href="http://localhost:8080/<app>/<service>.svc/ParagonRawAgents">...</a>
     **/
    public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo,
                                     ContentType responseFormat) throws SerializerException {

        // 1st we have retrieve the requested EntitySet from the uriInfo object (representation of the parsed service URI)
        var resourcePaths = uriInfo.getUriResourceParts();
        var uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in our example, the first segment is the EntitySet
        var edmEntitySet = uriResourceEntitySet.getEntitySet();

        // 2nd: fetch the data from backend for this requested EntitySetName // it has to be delivered as EntitySet object
        var entitySet = storage.getData(edmEntitySet);

        // 3rd: create a serializer based on the requested format (json)
        var serializer = odata.createSerializer(responseFormat);

        // 4th: Now serialize the content: transform from the EntitySet object to InputStream
        var edmEntityType = edmEntitySet.getEntityType();
        var contextUrl = ContextURL.with().entitySet(edmEntitySet).build();

        final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
        var opts =
                EntityCollectionSerializerOptions.with().id(id).contextURL(contextUrl).build();
        var serializedContent = serializer.entityCollection(serviceMetadata, edmEntityType, entitySet, opts);

        // Finally: configure the response object: set the body, headers and status code
        response.setContent(serializedContent.getContent());
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    }
}
