package org.symbolmath.parser;

import junit.framework.TestCase;
import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.leaf.IdentifierElement;
import org.symbolmath.ast.leaf.IntegerElement;
import org.symbolmath.ast.operation.NegativeOperation;

public class ParserTest extends TestCase {

  public void testWorksWithEmptyInput() {
    new Parser("", true).parse();
  }

  public void testInt() {
    ASTElement element = new Parser("1", true).parse();
    assertTrue(element instanceof IntegerElement);
  }

  public void testPositiveInt() {
    ASTElement element = new Parser("+1", true).parse();
    assertTrue(element instanceof IntegerElement);
  }

  public void testNegativeInt() {
    ASTElement element = new Parser("-1", true).parse();
    assertTrue(element instanceof NegativeOperation);
    assertTrue(element.getChildren().get(0) instanceof IntegerElement);
  }

  public void testVariable() {
    ASTElement element = new Parser("x", true).parse();
    assertTrue(element instanceof IdentifierElement);
  }

  public void testPositiveVariable() {
    ASTElement element = new Parser("+x", true).parse();
    assertTrue(element instanceof IdentifierElement);
  }

  public void testNegativeVariable() {
    ASTElement element = new Parser("-x", true).parse();
    assertTrue(element instanceof NegativeOperation);
    assertTrue(element.getChildren().get(0) instanceof IdentifierElement);
  }

  public void testIntInParens() {
    ASTElement element = new Parser("(1)", true).parse();
    assertTrue(element instanceof IntegerElement);
  }

  public void testNegativeIntInParens() {
    ASTElement element = new Parser("-(1)", true).parse();
    assertTrue(element instanceof NegativeOperation);
  }

  public void testVariableInParens() {
    ASTElement element = new Parser("(x)", true).parse();
    assertTrue(element instanceof  IdentifierElement);
  }

  public void testNegativeVariableInParens() {
    ASTElement element = new Parser("-(x)", true).parse();
    assertTrue(element instanceof NegativeOperation);
  }
}
