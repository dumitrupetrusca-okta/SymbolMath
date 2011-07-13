package org.symbolmath.ast.expression;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.leaf.IntegerElement;

import java.util.ArrayList;
import java.util.List;

public class MultiplicativeExpression extends MathExpression {
  private int multiplier;

  private MultiplicativeExpression(int multiplier, List<ASTElement> terms) {
    super(terms);
    this.multiplier = multiplier;
  }

  public static MathExpression create(List<ASTElement> terms) {
    int multiplier = 1;
    List<ASTElement> newTerms = new ArrayList<ASTElement>(terms.size());
    for (ASTElement term : terms) {
      if (term instanceof IntegerElement) {
        multiplier *= ((IntegerElement)term).getValue();
      } else {
        newTerms.add(term);
      }
    }
    return new MultiplicativeExpression(multiplier, newTerms);
  }

  public void join(MultiplicativeExpression expression) {
    this.multiplier += expression.multiplier;
  }

  @Override
  public String toString() {
    return (multiplier != 1 ? multiplier : "") + getSignature();
  }

  @Override
  public String getSignature() {
    String s = "";
    for (ASTElement term : terms) {
      s += "" + term;
    }
    return s;
  }
}
