package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.expression.MultiplicativeExpression;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class MultiplyOperation extends BinaryOperation {

  public MultiplyOperation(ASTElement left, ASTElement right) {
    super(left, right);
  }

  @Override
  public String toString() {
    return left + "*" + right;
  }

}
