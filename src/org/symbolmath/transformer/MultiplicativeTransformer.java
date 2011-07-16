package org.symbolmath.transformer;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.expression.MultiplicativeExpression;
import org.symbolmath.ast.operation.BinaryOperation;
import org.symbolmath.ast.operation.MultiplyOperation;

import java.util.ArrayList;
import java.util.List;

public class MultiplicativeTransformer implements ExpressionTransformer {

  public boolean canTransform(ASTElement operation) {
    return operation instanceof MultiplyOperation;
  }

  public MathExpression transform(BinaryOperation operation) {
    List<ASTElement> terms = new ArrayList<ASTElement>();
    collectTerms(operation, terms);
    return MultiplicativeExpression.create(terms);
  }

  private void collectTerms(BinaryOperation operation, List<ASTElement> terms) {
    ASTElement left = operation.getLeft();
    if (canTransform(left)) {
      collectTerms((BinaryOperation) left, terms);
    } else {
      terms.add(left);
    }
    ASTElement right = operation.getRight();
    if (canTransform(right)) {
      collectTerms((BinaryOperation) right, terms);
    } else {
      terms.add(right);
    }
  }

}
