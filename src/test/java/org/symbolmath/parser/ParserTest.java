package org.symbolmath.parser;

import org.junit.jupiter.api.Test;
import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.leaf.IdentifierElement;
import org.symbolmath.ast.leaf.IntegerElement;
import org.symbolmath.ast.expression.NegativeTerm;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testWorksWithEmptyInput() {
        new Parser("", true).parse();
    }

    @Test
    public void testInt() {
        ASTElement element = new Parser("1", true).parse();
        assertTrue(element instanceof IntegerElement);
    }

    @Test
    public void testPositiveInt() {
        ASTElement element = new Parser("+1", true).parse();
        assertTrue(element instanceof IntegerElement);
    }

    @Test
    public void testNegativeInt() {
        ASTElement element = new Parser("-1", true).parse();
        assertTrue(element instanceof NegativeTerm);
        assertTrue(element.getChildren().get(0) instanceof IntegerElement);
    }

    @Test
    public void testVariable() {
        ASTElement element = new Parser("x", true).parse();
        assertTrue(element instanceof IdentifierElement);
    }

    @Test
    public void testPositiveVariable() {
        ASTElement element = new Parser("+x", true).parse();
        assertTrue(element instanceof IdentifierElement);
    }

    @Test
    public void testNegativeVariable() {
        ASTElement element = new Parser("-x", true).parse();
        assertTrue(element instanceof NegativeTerm);
        assertTrue(element.getChildren().get(0) instanceof IdentifierElement);
    }

    @Test
    public void testIntInParens() {
        ASTElement element = new Parser("(1)", true).parse();
        assertTrue(element instanceof IntegerElement);
    }

    @Test
    public void testNegativeIntInParens() {
        ASTElement element = new Parser("-(1)", true).parse();
        assertTrue(element instanceof NegativeTerm);
    }

    @Test
    public void testVariableInParens() {
        ASTElement element = new Parser("(x)", true).parse();
        assertTrue(element instanceof IdentifierElement);
    }

    @Test
    public void testNegativeVariableInParens() {
        ASTElement element = new Parser("-(x)", true).parse();
        assertTrue(element instanceof NegativeTerm);
    }
}
