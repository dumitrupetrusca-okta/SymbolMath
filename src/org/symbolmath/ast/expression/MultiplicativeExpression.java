package org.symbolmath.ast.expression;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.leaf.IntegerElement;
import org.symbolmath.ast.operation.NegativeOperation;

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
      multiplier = process(multiplier, term, newTerms);
    }
    MultiplicativeExpression expression = new MultiplicativeExpression(multiplier, newTerms);
    return expression;
  }

  private static int process(int multiplier, ASTElement term, List<ASTElement> newTerms) {
    if (term instanceof NegativeOperation) {
      multiplier = process(multiplier, term.getChildren().get(0), newTerms);
      multiplier *= -1;
    } else if (term instanceof IntegerElement) {
      multiplier *= ((IntegerElement)term).getValue();
    } else {
      newTerms.add(term);
    }
    return multiplier;
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

  public int getMultiplier() {
    return multiplier;
  }
}
