package org.symbolmath.ast.operation;

import org.symbolmath.ast.ASTElement;

public class SubtractOperation extends BinaryOperation {

  public SubtractOperation(ASTElement left, ASTElement right) {
    super(left, right);
  }

  @Override
  public String toString() {
    return left + "-" + right;
  }
}
