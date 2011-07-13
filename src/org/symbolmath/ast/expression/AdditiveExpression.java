package org.symbolmath.ast.expression;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.operation.PowerOperation;

import java.util.Collection;
import java.util.List;

public class AdditiveExpression extends MathExpression {

  public AdditiveExpression(List<ASTElement> terms) {
    super(terms);
  }

  public ASTElement tryMerging() {
    Multimap<String, ASTElement> mapping = LinkedListMultimap.create();
    for (ASTElement term : terms) {
      MathExpression expr = (MathExpression) term;
      mapping.put(expr.getSignature(), term);
    }
    for (String key : mapping.keySet()) {
      Collection<ASTElement> elements = mapping.get(key);
      if (elements.size() > 1) {
        MultiplicativeExpression joinedExpression = null;
        for (ASTElement astElement : elements) {
          if (!(astElement instanceof MultiplicativeExpression) && !(astElement instanceof PowerOperation)) {
            throw new RuntimeException("Must be a MultiplicativeExpression or a PowerOperation");
          }
          if (joinedExpression == null) {
            joinedExpression = (MultiplicativeExpression) astElement;
          } else {
            joinedExpression.join((MultiplicativeExpression) astElement);
            this.removeChild(astElement);
          }
        }
      }
    }
    return this;
  }

  @Override
  public String toString() {
    return getSignature();
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
