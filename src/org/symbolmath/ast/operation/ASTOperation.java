package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.MathExpression;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class ASTOperation extends ASTElement {

  public MathExpression createExpression() {
    throw new NotImplementedException();
  }

}
