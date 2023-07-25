package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import org.apache.commons.io.IOUtils;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.commons.core.edm.primitivetype.EdmString;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.UriResourceProperty;
import org.apache.olingo.server.core.uri.UriParameterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link NnmrlsEntityPrimitiveProcessor}
 *
 * @author Oleksii Usatov
 */
@ExtendWith(MockitoExtension.class)
public class NnmrlsEntityPrimitiveProcessorTest {
    @Mock
    private Olingo4Storage olingo4Storage;
    @Mock
    private ODataRequest request;
    @Mock
    private ODataResponse response;
    @Mock
    private UriInfo uriInfo;
    @Mock
    private UriResourceProperty uriResourceProperty;
    @Mock
    private UriResourceEntitySet uriResourceEntitySet;
    @Mock
    private EdmEntitySet edmEntitySet;
    @Mock
    private OData oData;
    @Mock
    private ServiceMetadata serviceMetadata;
    @Mock
    private ODataSerializer oDataSerializer;
    @Mock
    private EdmProperty edmProperty;
    private UriParameterImpl uriParameter;
    private NnmrlsEntityPrimitiveProcessor nnmrlsEntityPrimitiveProcessor;

    @BeforeEach
    public void beforeEach() throws SerializerException {
        reset(olingo4Storage, request, response, uriInfo, uriResourceProperty,
                edmEntitySet, oData, serviceMetadata, oDataSerializer, edmProperty);
        nnmrlsEntityPrimitiveProcessor = new NnmrlsEntityPrimitiveProcessor(olingo4Storage);
        nnmrlsEntityPrimitiveProcessor.init(oData, serviceMetadata);

        when(uriResourceEntitySet.getEntitySet()).thenReturn(edmEntitySet);

        when(edmProperty.getName()).thenReturn("column1");
        when(edmProperty.getType()).thenReturn(new EdmString());
        when(uriResourceProperty.getProperty()).thenReturn(edmProperty);

        uriParameter = new UriParameterImpl();
        uriParameter.setName("id");
        uriParameter.setText("value");

        when(uriResourceEntitySet.getKeyPredicates()).thenReturn(List.of(uriParameter));

        when(uriInfo.getUriResourceParts()).thenReturn(List.of(
                uriResourceEntitySet,
                uriResourceProperty
        ));
    }

    @Test
    void testReadPrimitiveNotFound() {
        // Setup
        var entity = mock(Entity.class);
        doReturn(Optional.of(entity)).when(olingo4Storage)
                .getDataByKeys(edmEntitySet, List.of(uriParameter), List.of("column1", "id"));

        // Execute
        var thrown = assertThrows(ODataApplicationException.class,
                () -> nnmrlsEntityPrimitiveProcessor.readPrimitive(request, response, uriInfo, ContentType.JSON),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Property not found", thrown.getMessage());
    }

    @Test
    void testReadPrimitive() throws SerializerException, ODataApplicationException {

        // Setup
        doReturn(oDataSerializer).when(oData).createSerializer(any());
        var serializerResult = mock(SerializerResult.class);
        var inputStream = IOUtils.toInputStream("content", StandardCharsets.UTF_8);
        when(serializerResult.getContent()).thenReturn(inputStream);
        when(oDataSerializer.primitive(any(), any(), any(), any())).thenReturn(serializerResult);

        var entity = mock(Entity.class);
        var property = mock(Property.class);
        when(property.getValue()).thenReturn("value");
        when(entity.getProperty("column1")).thenReturn(property);
        doReturn(Optional.of(entity)).when(olingo4Storage)
                .getDataByKeys(edmEntitySet, List.of(uriParameter), List.of("column1", "id"));


        // Execute
        nnmrlsEntityPrimitiveProcessor.readPrimitive(request, response, uriInfo, ContentType.JSON);

        // Verify
        verify(response).setContent(inputStream);
        verify(response).setStatusCode(HttpStatusCode.OK.getStatusCode());
        verify(response).setHeader(HttpHeader.CONTENT_TYPE, ContentType.JSON.toContentTypeString());
    }
}
