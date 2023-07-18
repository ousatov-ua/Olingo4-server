package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import org.apache.commons.io.IOUtils;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriInfoResource;
import org.apache.olingo.server.api.uri.UriParameter;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.UriResourceKind;
import org.apache.olingo.server.api.uri.queryoption.SelectItem;
import org.apache.olingo.server.core.uri.UriInfoImpl;
import org.apache.olingo.server.core.uri.UriParameterImpl;
import org.apache.olingo.server.core.uri.queryoption.SelectItemImpl;
import org.apache.olingo.server.core.uri.queryoption.SelectOptionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NnmrlsEntityProcessorTest {

    @Mock
    private Olingo4Storage olingo4Storage;
    @Mock
    private ODataRequest request;
    @Mock
    private ODataResponse response;
    @Mock
    private UriInfo uriInfo;
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
    private InputStream inputStream;
    private UriParameterImpl uriParameter;

    private NnmrlsEntityProcessor nnmrlsEntityProcessor;

    @BeforeEach
    public void beforeEach() throws SerializerException {
        reset(olingo4Storage, request, response, uriInfo, uriResourceEntitySet,
                edmEntitySet, oData, serviceMetadata, oDataSerializer);


        nnmrlsEntityProcessor = new NnmrlsEntityProcessor(olingo4Storage);
        nnmrlsEntityProcessor.init(oData, serviceMetadata);
        doReturn(oDataSerializer).when(oData).createSerializer(any());
        var serializerResult = mock(SerializerResult.class);
        inputStream = IOUtils.toInputStream("content", StandardCharsets.UTF_8);
        when(serializerResult.getContent()).thenReturn(inputStream);
        when(oDataSerializer.entity(any(), any(), any(), any())).thenReturn(serializerResult);
        when(uriResourceEntitySet.getEntitySet()).thenReturn(edmEntitySet);
        uriParameter = new UriParameterImpl();
        uriParameter.setName("id");
        uriParameter.setText("value");

        when(uriResourceEntitySet.getKeyPredicates()).thenReturn(List.of(uriParameter));

        when(uriInfo.getUriResourceParts()).thenReturn(List.of(
                uriResourceEntitySet
        ));
        var selectOption = new SelectOptionImpl();
        var selectItem1 = new SelectItemImpl();
        var uriInfoResource1 = new UriInfoImpl();
        uriInfoResource1.addResourcePart(new UriResource() {
            @Override
            public UriResourceKind getKind() {
                return UriResourceKind.value;
            }

            @Override
            public String getSegmentValue() {
                return "column1";
            }
        });
        selectItem1.setResourcePath(uriInfoResource1);

        var selectItem2 = new SelectItemImpl();
        var uriInfoResource2 = new UriInfoImpl();
        uriInfoResource2.addResourcePart(new UriResource() {
            @Override
            public UriResourceKind getKind() {
                return UriResourceKind.value;
            }

            @Override
            public String getSegmentValue() {
                return "column2";
            }
        });
        selectItem2.setResourcePath(uriInfoResource2);

        var selectItems = List.of(
                selectItem1, (SelectItem) selectItem2
        );
        selectOption.setSelectItems(selectItems);
        when(uriInfo.getSelectOption()).thenReturn(selectOption);
    }

    @Test
    void testReadEntity() throws SerializerException, ODataApplicationException {

        // Setup
        var entity = mock(Entity.class);
        doReturn(Optional.of(entity)).when(olingo4Storage)
                .getDataByKeys(edmEntitySet, List.of(uriParameter), List.of("column1", "column2", "id"));

        // Execute
        nnmrlsEntityProcessor.readEntity(request, response, uriInfo, ContentType.JSON);

        // Verify
        verify(response).setContent(inputStream);
        verify(response).setStatusCode(HttpStatusCode.OK.getStatusCode());
        verify(response).setHeader(HttpHeader.CONTENT_TYPE, ContentType.JSON.toContentTypeString());
    }
}
