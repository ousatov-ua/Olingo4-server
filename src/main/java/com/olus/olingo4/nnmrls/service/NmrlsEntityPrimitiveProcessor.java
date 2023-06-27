package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.edm.EdmPrimitiveType;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.deserializer.DeserializerException;
import org.apache.olingo.server.api.processor.PrimitiveProcessor;
import org.apache.olingo.server.api.serializer.PrimitiveSerializerOptions;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.UriResourceProperty;

import java.util.Locale;

/**
 * Processor to read some primitive from Entity
 *
 * @author Oleksii Usatov
 */
public class NmrlsEntityPrimitiveProcessor implements PrimitiveProcessor {

    private final Olingo4Storage storage;
    private OData odata;
    private ServiceMetadata serviceMetadata;

    public NmrlsEntityPrimitiveProcessor(Olingo4Storage storage) {
        this.storage = storage;
    }

    public void init(OData odata, ServiceMetadata serviceMetadata) {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
    }

    /*
     * In our example, the URL would be: http://localhost:8080/<app>/<service>.svc/SomeEntity(1)/Name
     * and the response:
     * {
     *  @odata.context: "$metadata#SomeEntity/Name",
     *  value: "Some name"
     * }
     */
    public void readPrimitive(ODataRequest request, ODataResponse response,
                              UriInfo uriInfo, ContentType responseFormat)
            throws ODataApplicationException, SerializerException {

        // Retrieve info from URI
        // Retrieve the info about the requested entity set
        var resourceParts = uriInfo.getUriResourceParts();

        // Only in our application we can rely that the first segment is the EntitySet
        var uriEntityset = (UriResourceEntitySet) resourceParts.get(0);
        var edmEntitySet = uriEntityset.getEntitySet();

        // The key for the entity
        var keyPredicates = uriEntityset.getKeyPredicates();

        // Retrieve the requested (Edm) property
        var uriProperty = (UriResourceProperty) resourceParts.get(resourceParts.size() - 1); // the last segment is the Property
        var edmProperty = uriProperty.getProperty();
        var edmPropertyName = edmProperty.getName();

        // We know we have only primitive types in our model
        var edmPropertyType = (EdmPrimitiveType) edmProperty.getType();

        /*
          Retrieve data from backend
          Retrieve the entity data, for which the property has to be read
         */
        var entityOpt = storage.getDataByKeys(edmEntitySet, keyPredicates);
        if (entityOpt.isEmpty()) { // Bad request
            throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
        }

        var entity = entityOpt.get();

        // Retrieve the property data from the entity
        var property = entity.getProperty(edmPropertyName);
        if (property == null) {
            throw new ODataApplicationException("Property not found", HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
        }

        // Serialize
        var value = property.getValue();
        if (value != null) {

            // Configure the serializer
            var serializer = odata.createSerializer(responseFormat);

            var contextUrl = ContextURL.with().entitySet(edmEntitySet).navOrPropertyPath(edmPropertyName).build();
            var options = PrimitiveSerializerOptions.with().contextURL(contextUrl).build();

            // Serialize
            var serializerResult = serializer.primitive(serviceMetadata, edmPropertyType, property, options);
            var propertyStream = serializerResult.getContent();

            // Configure the response object
            response.setContent(propertyStream);
            response.setStatusCode(HttpStatusCode.OK.getStatusCode());
            response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
        } else {

            // In case there's no value for the property, we can skip the serialization
            response.setStatusCode(HttpStatusCode.NO_CONTENT.getStatusCode());
        }
    }

    public void updatePrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat, ContentType responseFormat)
            throws ODataApplicationException, DeserializerException, SerializerException {
        throw new ODataApplicationException("Not supported.", HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
    }

    public void deletePrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo) throws ODataApplicationException {
        throw new ODataApplicationException("Not supported.", HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
    }
}