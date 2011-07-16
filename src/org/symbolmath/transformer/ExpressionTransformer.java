package org.symbolmath.transformer;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.operation.BinaryOperation;

public interface ExpressionTransformer {

  boolean canTransform(ASTElement operation);

  MathExpression transform(BinaryOperation operation);

}
