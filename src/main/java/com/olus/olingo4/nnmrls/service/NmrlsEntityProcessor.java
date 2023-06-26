package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import com.olus.olingo4.nnmrls.util.Util;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.deserializer.DeserializerException;
import org.apache.olingo.server.api.processor.EntityProcessor;
import org.apache.olingo.server.api.serializer.EntitySerializerOptions;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;

import java.util.Locale;

/**
 * Read a single Entity processor
 *
 * @author Oleksii Usatov
 */
public class NmrlsEntityProcessor implements EntityProcessor {
    private OData odata;
    private ServiceMetadata serviceMetadata;
    private final Olingo4Storage storage;

    public NmrlsEntityProcessor(Olingo4Storage storage) {
        this.storage = storage;
    }

    public void init(OData odata, ServiceMetadata serviceMetadata) {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
    }

    public void readEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType responseFormat)
            throws SerializerException, ODataApplicationException {

        // Retrieve the Entity Type
        var resourcePaths = uriInfo.getUriResourceParts();

        // Note: we assume that the first segment is the EntitySet
        var uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);
        var edmEntitySet = uriResourceEntitySet.getEntitySet();

        // Retrieve the data from backend
        var keyPredicates = uriResourceEntitySet.getKeyPredicates();
        var entityOpt = storage.getDataByKeys(edmEntitySet, keyPredicates);
        if (entityOpt.isEmpty()) {
            throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(),
                    Locale.US);
        }
        var entity = entityOpt.get();

        // Serialize
        var entityType = edmEntitySet.getEntityType();

        var contextUrl = ContextURL.with().entitySet(edmEntitySet).suffix(ContextURL.Suffix.ENTITY).build();

        // Expand and select currently not supported
        var options = EntitySerializerOptions.with().contextURL(contextUrl).build();

        var serializer = this.odata.createSerializer(responseFormat);
        var serializerResult = serializer.entity(serviceMetadata, entityType, entity, options);
        var entityStream = serializerResult.getContent();

        // Configure the response object
        response.setContent(entityStream);
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    }

    public void createEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat, ContentType responseFormat)
            throws ODataApplicationException, DeserializerException, SerializerException {

        // Retrieve the entity type from the URI
        var edmEntitySet = Util.getEdmEntitySet(uriInfo);
        var edmEntityType = edmEntitySet.getEntityType();

        // Create the data in backend
        // Retrieve the payload from the POST request for the entity to create and deserialize it
        var requestInputStream = request.getBody();
        var deserializer = this.odata.createDeserializer(requestFormat);
        var result = deserializer.entity(requestInputStream, edmEntityType);
        var requestEntity = result.getEntity();

        // Do the creation in backend, which returns the newly created entity
        var createdEntity = storage.createEntityData(edmEntitySet, requestEntity);

        // Serialize the response (we have to return the created entity)
        var contextUrl = ContextURL.with().entitySet(edmEntitySet).build();
        var options = EntitySerializerOptions.with().contextURL(contextUrl).build(); // expand and select currently not supported

        var serializer = this.odata.createSerializer(responseFormat);
        var serializedResponse = serializer.entity(serviceMetadata, edmEntityType, createdEntity, options);

        // Configure the response object
        response.setContent(serializedResponse.getContent());
        response.setStatusCode(HttpStatusCode.CREATED.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    }

    public void updateEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat, ContentType responseFormat)
            throws ODataApplicationException, DeserializerException, SerializerException {
        throw new ODataApplicationException("Not supported.", HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
    }

    public void deleteEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo) throws ODataApplicationException {
        throw new ODataApplicationException("Not supported.", HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
    }
}