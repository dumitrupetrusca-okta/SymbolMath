package org.symbolmath.transformer;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.operation.AddOperation;
import org.symbolmath.ast.operation.BinaryOperation;
import org.symbolmath.ast.operation.NegativeOperation;
import org.symbolmath.ast.operation.SubtractOperation;

import java.util.ArrayList;
import java.util.List;

public class AdditiveTransformer implements ExpressionTransformer {

  public boolean canTransform(ASTElement operation) {
    return operation instanceof AddOperation || operation instanceof SubtractOperation;
  }

  public MathExpression transform(BinaryOperation operation) {
    List<ASTElement> terms = new ArrayList<ASTElement>();
    collectTerms(operation, terms);
    return new AdditiveExpression(terms);
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
      if (right.getParent() instanceof SubtractOperation) {
        terms.add(new NegativeOperation(right));
      } else {
        terms.add(right);
      }
    }
  }

}
