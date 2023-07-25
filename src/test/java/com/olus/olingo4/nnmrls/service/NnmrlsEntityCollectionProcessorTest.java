package com.olus.olingo4.nnmrls.service;

import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import org.apache.commons.io.IOUtils;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.commons.core.edm.EdmKeyPropertyRefImpl;
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
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.UriResourceKind;
import org.apache.olingo.server.api.uri.queryoption.SelectItem;
import org.apache.olingo.server.api.uri.queryoption.expression.MethodKind;
import org.apache.olingo.server.core.uri.UriInfoImpl;
import org.apache.olingo.server.core.uri.UriParameterImpl;
import org.apache.olingo.server.core.uri.UriResourcePrimitivePropertyImpl;
import org.apache.olingo.server.core.uri.queryoption.FilterOptionImpl;
import org.apache.olingo.server.core.uri.queryoption.SelectItemImpl;
import org.apache.olingo.server.core.uri.queryoption.SelectOptionImpl;
import org.apache.olingo.server.core.uri.queryoption.expression.LiteralImpl;
import org.apache.olingo.server.core.uri.queryoption.expression.MemberImpl;
import org.apache.olingo.server.core.uri.queryoption.expression.MethodImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link NnmrlsEntityCollectionProcessor}
 *
 * @author Oleksii Usatov
 */
@ExtendWith(MockitoExtension.class)
public class NnmrlsEntityCollectionProcessorTest {
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
    @Mock
    private EdmEntityType edmEntityType;
    @Mock
    private EdmKeyPropertyRef edmKeyPropertyRef;
    private NnmrlsEntityCollectionProcessor nnmrlsEntityCollectionProcessor;
    private InputStream inputStream;

    @BeforeEach
    public void beforeEach() throws SerializerException {

        // Setup
        reset(olingo4Storage, request, response, uriInfo, uriResourceEntitySet,
                edmEntitySet, oData, serviceMetadata, oDataSerializer, edmEntitySet,
                edmKeyPropertyRef);
        nnmrlsEntityCollectionProcessor = new NnmrlsEntityCollectionProcessor(olingo4Storage);
        nnmrlsEntityCollectionProcessor.init(oData, serviceMetadata);
        doReturn(oDataSerializer).when(oData).createSerializer(any());
        var serializerResult = mock(SerializerResult.class);
        inputStream = IOUtils.toInputStream("content", StandardCharsets.UTF_8);
        when(serializerResult.getContent()).thenReturn(inputStream);
        when(oDataSerializer.entityCollection(any(), any(), any(), any())).thenReturn(serializerResult);

        when(uriResourceEntitySet.getEntitySet()).thenReturn(edmEntitySet);
        var csdlPropertyRef = new CsdlPropertyRef();
        csdlPropertyRef.setName("id");
        when(edmEntityType.getKeyPropertyRefs()).thenReturn(List.of(new EdmKeyPropertyRefImpl(edmEntityType, csdlPropertyRef)));
        when(edmEntitySet.getEntityType()).thenReturn(edmEntityType);
        var uriParameter = new UriParameterImpl();
        uriParameter.setName("id");
        uriParameter.setText("value");

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
    void testReadEntityCollection() throws SerializerException, ODataApplicationException {

        // Setup
        var entityCollection = mock(EntityCollection.class);
        when(olingo4Storage.getData(edmEntitySet, -1, -1, List.of("column1", "column2", "id"))).thenReturn(
                entityCollection
        );

        // Execute
        nnmrlsEntityCollectionProcessor.readEntityCollection(request, response, uriInfo, ContentType.JSON);

        // Verify
        verify(response).setContent(inputStream);
        verify(response).setStatusCode(HttpStatusCode.OK.getStatusCode());
        verify(response).setHeader(HttpHeader.CONTENT_TYPE, ContentType.JSON.toContentTypeString());
    }

    @Test
    void testReadEntityCollectionWithFilter() throws SerializerException, ODataApplicationException {

        // Setup
        var entityCollection = mock(EntityCollection.class);
        var list = new LinkedList<Entity>();

        var entity1 = new Entity();
        var property1 = new Property();
        property1.setName("column1");
        property1.setValue(ValueType.PRIMITIVE, "another");
        entity1.addProperty(property1);
        list.add(entity1);

        var entity2 = new Entity();
        var property2 = new Property();
        property2.setName("column1");
        property2.setValue(ValueType.PRIMITIVE, "test");
        entity2.addProperty(property2);
        entity2.addProperty(property1);
        list.add(entity2);

        when(entityCollection.getEntities()).thenReturn(list);
        when(olingo4Storage.getData(edmEntitySet, -1, -1, List.of("column1", "column2", "id"))).thenReturn(
                entityCollection
        );

        var filteUriInfo = new UriInfoImpl();
        var edmProperty = mock(EdmProperty.class);
        when(edmProperty.getName()).thenReturn("column1");
        var uriResourcePrimitiveProperty = new UriResourcePrimitivePropertyImpl(edmProperty);
        filteUriInfo.addResourcePart(uriResourcePrimitiveProperty);
        var filterOption = spy(new FilterOptionImpl());
        var expression = new MethodImpl(MethodKind.CONTAINS, List.of(new MemberImpl(filteUriInfo, new EdmString()),
                new LiteralImpl("'test'", new EdmString())));


        filterOption.setExpression(expression);
        when(uriInfo.getFilterOption()).thenReturn(filterOption);

        // Execute
        nnmrlsEntityCollectionProcessor.readEntityCollection(request, response, uriInfo, ContentType.JSON);

        // Verify
        verify(filterOption, times(2)).getExpression();
        verify(response).setContent(inputStream);
        verify(response).setStatusCode(HttpStatusCode.OK.getStatusCode());
        verify(response).setHeader(HttpHeader.CONTENT_TYPE, ContentType.JSON.toContentTypeString());
        assertEquals(List.of(entity2), entityCollection.getEntities());
    }
}
