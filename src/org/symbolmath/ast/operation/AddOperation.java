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

  @Override
  public MathExpression createExpression() {
    List<ASTElement> terms = new ArrayList<ASTElement>();
    collectTerms(this, terms);
    return new AdditiveExpression(terms);
  }

  private void collectTerms(AddOperation addOp, List<ASTElement> terms) {
    ASTElement left = addOp.getLeft();
    if (left instanceof AddOperation) {
      collectTerms((AddOperation) left, terms);
    } else {
      terms.add(left);
    }
    ASTElement right = addOp.getRight();
    if (right instanceof AddOperation) {
      collectTerms((AddOperation) right, terms);
    } else {
      terms.add(right);
    }
  }
}
