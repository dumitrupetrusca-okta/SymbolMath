package org.symbolmath.ast.expression;

import org.symbolmath.ast.ASTElement;

import java.util.List;

public class AdditiveExpression extends MathExpression {

  public AdditiveExpression(List<ASTElement> terms) {
    super(terms);
  }

  @Override
  public String toString() {
    return null;
  }

  public List<ASTElement> getTerms() {
    return terms;
  }

  public ASTElement tryMerging() {
    for (ASTElement term : terms) {
      MultiplicativeExpression expr = (MultiplicativeExpression) term;
    }
    return null;
  }
}
