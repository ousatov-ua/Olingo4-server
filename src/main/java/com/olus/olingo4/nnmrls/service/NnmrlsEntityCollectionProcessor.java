package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.queryoption.SkipOption;
import org.apache.olingo.server.api.uri.queryoption.TopOption;
import org.apache.olingo.server.api.uri.queryoption.expression.ExpressionVisitException;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
                                     ContentType responseFormat) throws SerializerException, ODataApplicationException {

        // We have retrieve the requested EntitySet from the uriInfo object (representation of the parsed service URI)
        var resourcePaths = uriInfo.getUriResourceParts();
        var uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in our app, the first segment is the EntitySet
        var edmEntitySet = uriResourceEntitySet.getEntitySet();

        int offset = -1;
        SkipOption skipOption = uriInfo.getSkipOption();
        if (skipOption != null) {
            offset = skipOption.getValue();
        }
        int limit = -1;
        TopOption topOption = uriInfo.getTopOption();
        if (topOption != null) {
            limit = topOption.getValue();
        }

        // Get select options
        var selectOption = uriInfo.getSelectOption();
        var filterOption = uriInfo.getFilterOption();
        List<String> selectedColumns = Collections.emptyList();
        if (selectOption != null) {
            selectedColumns = selectOption.getSelectItems()
                    .stream()
                    .map(option ->
                            option.getResourcePath().getUriResourceParts().get(option.getResourcePath().getUriResourceParts().size() - 1).getSegmentValue())
                    .collect(Collectors.toList());
            selectedColumns.add(edmEntitySet.getEntityType().getKeyPropertyRefs().get(0).getName());
        }

        List<String> columns = Collections.emptyList();
        if (!selectedColumns.isEmpty()) {
            columns = selectedColumns;
        }

        // Fetch the data from backend for this requested EntitySetName // it has to be delivered as EntitySet object
        var entitySet = storage.getData(edmEntitySet, offset, limit, columns);

        if (filterOption != null) {

            // Apply $filter system query option
            try {
                List<Entity> entityList = entitySet.getEntities();
                Iterator<Entity> entityIterator = entityList.iterator();

                // Evaluate the expression for each entity
                // If the expression is evaluated to "true", keep the entity otherwise remove it from the entityList
                while (entityIterator.hasNext()) {

                    // To evaluate the expression, create an instance of the Filter Expression Visitor and pass
                    // the current entity to the constructor
                    var currentEntity = entityIterator.next();
                    var filterExpression = filterOption.getExpression();
                    var expressionVisitor = new FilterExpressionVisitor(currentEntity);

                    // Start evaluating the expression
                    Object visitorResult = filterExpression.accept(expressionVisitor);

                    // The result of the filter expression must be of type Edm.Boolean
                    if (visitorResult instanceof Boolean) {
                        if (!Boolean.TRUE.equals(visitorResult)) {

                            // The expression evaluated to false (or null), so we have to remove the currentEntity from entityList
                            entityIterator.remove();
                        }
                    } else {
                        throw new ODataApplicationException("A filter expression must evaluate to type Edm.Boolean",
                                HttpStatusCode.BAD_REQUEST.getStatusCode(), Locale.ENGLISH);
                    }
                }
            } catch (ExpressionVisitException e) {
                throw new ODataApplicationException("Exception in filter evaluation",
                        HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode(), Locale.ENGLISH);
            }
        }

        // Create a serializer based on the requested format (json)
        var serializer = odata.createSerializer(responseFormat);

        // Now serialize the content: transform from the EntitySet object to InputStream
        var edmEntityType = edmEntitySet.getEntityType();
        var contextUrl = ContextURL.with().entitySet(edmEntitySet).build();

        final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
        var opts =
                EntityCollectionSerializerOptions.with()
                        .select(selectOption)
                        .id(id)
                        .contextURL(contextUrl)
                        .build();
        var serializedContent = serializer.entityCollection(serviceMetadata, edmEntityType, entitySet, opts);

        // Finally: configure the response object: set the body, headers and status code
        response.setContent(serializedContent.getContent());
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    }
}
