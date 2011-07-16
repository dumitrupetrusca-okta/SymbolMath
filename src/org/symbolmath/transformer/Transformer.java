package org.symbolmath.transformer;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.operation.BinaryOperation;
import org.symbolmath.parser.Parser;
import org.symbolmath.util.SymbolMathUtil;

public class Transformer {
  private static ExpressionTransformer[] transformers = {
      new MultiplicativeTransformer(),
      new AdditiveTransformer()
  };
  private ASTElement element;

  public Transformer(ASTElement element) {
    this.element = element;
  }

  public Transformer(String s) {
    this(new Parser(s, true).parse());
  }

  public ASTElement transform() {
    SymbolMathUtil.checkParents(this.element, null);
    for (ExpressionTransformer transformer : transformers) {
      this.element = transform(this.element, transformer);
      SymbolMathUtil.checkParents(this.element, null);
    }
    return this.element;
  }

  private ASTElement transform(ASTElement operation, ExpressionTransformer transformer) {
    if (transformer.canTransform(operation)) {
      MathExpression expression = transformer.transform((BinaryOperation) operation);
      ASTElement parent = operation.getParent();
      if (parent != null) {
        parent.replaceChild(operation, expression);
      }
      operation = expression;
    }

    for (ASTElement child : operation.getChildren()) {
      transform(child, transformer);
    }

    return operation;
  }

}
