package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;

public class PowerOperation extends BinaryOperation {

  public PowerOperation(ASTElement left, ASTElement right) {
    super(left, right);
  }

  @Override
  public String toString() {
    return left + "^" + right;
  }
}
