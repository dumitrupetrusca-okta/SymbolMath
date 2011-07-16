package org.symbolmath.transformer;

import junit.framework.TestCase;
import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.ast.expression.MultiplicativeExpression;
import org.symbolmath.ast.leaf.IdentifierElement;

public class TransformerTest extends TestCase {

  public void testChainedAdditions() {
    AdditiveExpression element = (AdditiveExpression) new Transformer("2+x+y").transform();
    assertEquals(3, element.getChildren().size());
  }

  public void testChainedSubtractions() {
    AdditiveExpression element = (AdditiveExpression) new Transformer("-2-x-y").transform();
    assertEquals(3, element.getChildren().size());
  }

  public void testChainedMultiplications() {
    MultiplicativeExpression element = (MultiplicativeExpression) new Transformer("2*x*y").transform();
    assertEquals(2, element.getChildren().size());
    assertEquals(2, element.getMultiplier());
  }

  public void testChainedDivisions() {
    ASTElement element = new Transformer("2/x/y").transform();
    fail();
  }

  public void testChainedPowers() {
    ASTElement element = new Transformer("2^x^y").transform();
    fail();
  }

  // more complex cases

  public void testCompoundMultiplier() {
    MultiplicativeExpression element = (MultiplicativeExpression) new Transformer("-2*3*x").transform();
    assertEquals(-6, element.getMultiplier());
  }

  public void testNegativeMultiplier() {
    MultiplicativeExpression element = (MultiplicativeExpression) new Transformer("-2*x").transform();
    assertEquals(-2, element.getMultiplier());
    assertEquals(1, element.getChildren().size());
    assertEquals(IdentifierElement.class, element.getChildren().get(0).getClass());
  }
}
