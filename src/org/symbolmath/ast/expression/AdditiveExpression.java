package org.symbolmath.ast.expression;

import org.symbolmath.ast.ASTElement;

import java.util.List;

public class AdditiveExpression extends MathExpression {

  public AdditiveExpression(List<ASTElement> terms) {
    super(terms);
  }

  @Override
  public String toString() {
    if (getChildren().size() > 1) {
      return "(" + getSignature() + ")";
    } else {
      return getSignature();
    }
  }

  @Override
  public String getSignature() {
    String s = "";
    for (int i = 0; i < terms.size(); i++) {
      s += "" + terms.get(i);
      s += (i != terms.size() - 1) ? " + " : "";
    }
    return s;
  }
}
