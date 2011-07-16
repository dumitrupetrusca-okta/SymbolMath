package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.expression.MultiplicativeExpression;

import java.util.ArrayList;
import java.util.List;

public class AddOperation extends BinaryOperation {

  public AddOperation(ASTElement left, ASTElement right) {
    super(left, right);
  }

  @Override
  public String toString() {
    return left + "+" + right;
  }

}
