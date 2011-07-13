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

  @Override
  public MathExpression createExpression() {
    List<ASTElement> terms = new ArrayList<ASTElement>();
    collectTerms(this, terms);
    return MultiplicativeExpression.create(terms);
  }

  private void collectTerms(MultiplyOperation addOp, List<ASTElement> terms) {
    ASTElement left = addOp.getLeft();
    if (left instanceof MultiplyOperation) {
      collectTerms((MultiplyOperation) left, terms);
    } else {
      terms.add(left);
    }
    ASTElement right = addOp.getRight();
    if (right instanceof MultiplyOperation) {
      collectTerms((MultiplyOperation) right, terms);
    } else {
      terms.add(right);
    }
  }
}
