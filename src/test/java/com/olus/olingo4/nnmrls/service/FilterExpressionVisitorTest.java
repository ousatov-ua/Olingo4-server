package com.olus.olingo4.nnmrls.service;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmEnumType;
import org.apache.olingo.commons.api.edm.EdmType;
import org.apache.olingo.commons.core.edm.primitivetype.EdmByte;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDecimal;
import org.apache.olingo.commons.core.edm.primitivetype.EdmDouble;
import org.apache.olingo.commons.core.edm.primitivetype.EdmInt16;
import org.apache.olingo.commons.core.edm.primitivetype.EdmInt32;
import org.apache.olingo.commons.core.edm.primitivetype.EdmInt64;
import org.apache.olingo.commons.core.edm.primitivetype.EdmSByte;
import org.apache.olingo.commons.core.edm.primitivetype.EdmSingle;
import org.apache.olingo.commons.core.edm.primitivetype.EdmString;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.queryoption.expression.BinaryOperatorKind;
import org.apache.olingo.server.api.uri.queryoption.expression.Expression;
import org.apache.olingo.server.api.uri.queryoption.expression.UnaryOperatorKind;
import org.apache.olingo.server.core.uri.queryoption.expression.LiteralImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Unit test for {@link FilterExpressionVisitor}
 *
 * @author Oleksii Usatov
 */
class FilterExpressionVisitorTest {

    @ParameterizedTest
    @ValueSource(classes = {EdmByte.class, EdmSByte.class, EdmInt16.class,
            EdmInt32.class, EdmInt64.class, EdmSingle.class, EdmDouble.class, EdmDecimal.class})
    void testVisitLiteralNumber(Class<EdmType> type) throws ODataApplicationException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // Setup
        var typeInstance = type.getDeclaredConstructor().newInstance();
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);
        var literal = new LiteralImpl("1", typeInstance);

        // Execute
        var result = visitor.visitLiteral(literal);

        // Verify
        assertTrue(result instanceof BigDecimal);
        assertEquals(new BigDecimal(1), result);
    }

    @Test
    void testVisitLiteralStringIncorrect() throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);
        var literal = new LiteralImpl("1", new EdmString());

        // Execute
        var result = visitor.visitLiteral(literal);

        // Verify
        assertTrue(result instanceof String);
        assertEquals("", result);
    }

    @Test
    void testVisitLiteralString() throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);
        var literal = new LiteralImpl("'1'", new EdmString());

        // Execute
        var result = visitor.visitLiteral(literal);

        // Verify
        assertTrue(result instanceof String);
        assertEquals("1", result);
    }

    @Test
    void testVisitUnaryOperatorBoolean() throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);

        // Execute && Verify
        assertFalse((Boolean) visitor.visitUnaryOperator(UnaryOperatorKind.NOT, true));
    }

    @Test
    void testVisitUnaryOperatorWrongType() {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);

        // Execute && Verify
        var thrown = assertThrows(ODataApplicationException.class,
                () -> visitor.visitUnaryOperator(UnaryOperatorKind.NOT, 1),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Invalid type for unary operator", thrown.getMessage());
    }

    @Test
    void testVisitUnaryOperatorNumber() throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);

        // Execute && Verify
        assertEquals(-1L, (Long) visitor.visitUnaryOperator(UnaryOperatorKind.MINUS, 1L));
        assertEquals(-1, (Integer) visitor.visitUnaryOperator(UnaryOperatorKind.MINUS, 1));
        assertEquals(-1.0d, (Double) visitor.visitUnaryOperator(UnaryOperatorKind.MINUS, 1.0d));
        assertEquals(-1.0f, (Float) visitor.visitUnaryOperator(UnaryOperatorKind.MINUS, 1.0f));
        assertEquals(new BigDecimal(-1), visitor.visitUnaryOperator(UnaryOperatorKind.MINUS, new BigDecimal(1)));
    }

    @ParameterizedTest
    @CsvSource({"ADD,1,1,2", "MOD,3,2,1", "MUL,2,2,4", "DIV,4,2,2", "SUB,4,1,3"})
    void testVisitBinaryArithmeticOperator(String operator, String left, String right, String result) throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);


        // Execute && Verify
        assertEquals(new BigDecimal(result), visitor.visitBinaryOperator(BinaryOperatorKind.valueOf(operator),
                Long.valueOf(left), new BigDecimal(right)));
    }

    @ParameterizedTest
    @CsvSource({"EQ,1,1,true", "NE,3,2,true", "NE,2,2,false", "GE,2,2,true", "GE,1,2,false", "GE,3,2,true",
            "GT,4,2,true", "GT,2,2,false", "GT,1,2,false", "LE,4,4,true", "LE,5,4,false", "LE,4,5,true",
            "LT,2,4,true", "LT,3,2,false"})
    void testVisitBinaryComparisonOperator(String operator, String left, String right, String result) throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);


        // Execute && Verify
        assertEquals(Boolean.valueOf(result), visitor.visitBinaryOperator(BinaryOperatorKind.valueOf(operator),
                new BigDecimal(left), new BigDecimal(right)));
    }

    @ParameterizedTest
    @CsvSource({"AND,true,true,true", "AND,false,true,false", "OR,true,false,true", "OR,false,false,false"})
    void testVisitBinaryBooleanOperator(String operator, String left, String right, String result) throws ODataApplicationException {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);


        // Execute && Verify
        assertEquals(Boolean.valueOf(result), visitor.visitBinaryOperator(BinaryOperatorKind.valueOf(operator),
                Boolean.valueOf(left), Boolean.valueOf(right)));
    }

    @Test
    void testVisitTypeLiteral() {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);
        var edmEntityType = mock(EdmEntityType.class);

        // Execute && Verify
        var thrown = assertThrows(ODataApplicationException.class,
                () -> visitor.visitTypeLiteral(edmEntityType),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Type literals are not implemented", thrown.getMessage());
    }

    @Test
    void testVisitAlias() {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);

        // Execute && Verify
        var thrown = assertThrows(ODataApplicationException.class,
                () -> visitor.visitAlias("name"),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Aliases are not implemented", thrown.getMessage());
    }

    @Test
    void testVisitEnum() {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);
        var edmEnumType = mock(EdmEnumType.class);

        // Execute && Verify
        var thrown = assertThrows(ODataApplicationException.class,
                () -> visitor.visitEnum(edmEnumType, List.of()),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Enums are not implemented", thrown.getMessage());
    }

    @Test
    void testVisitLambdaExpression() {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);
        var expression = mock(Expression.class);

        // Execute && Verify
        var thrown = assertThrows(ODataApplicationException.class,
                () -> visitor.visitLambdaExpression("", "", expression),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Lambda expressions are not implemented", thrown.getMessage());
    }

    @Test
    void testVisitLambdaReference() {

        // Setup
        var entity = new Entity();
        var visitor = new FilterExpressionVisitor(entity);

        // Execute && Verify
        var thrown = assertThrows(ODataApplicationException.class,
                () -> visitor.visitLambdaReference(""),
                "Expected doThing() to throw, but it didn't");
        assertEquals("Lambda references are not implemented", thrown.getMessage());
    }
}
