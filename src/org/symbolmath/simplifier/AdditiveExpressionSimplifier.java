package org.symbolmath.simplifier;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.ast.expression.MathExpression;
import org.symbolmath.ast.expression.MultiplicativeExpression;
import org.symbolmath.ast.operation.PowerOperation;

import java.util.Collection;

public class AdditiveExpressionSimplifier extends ExpressionSimplifier<AdditiveExpression> {

  public Class<AdditiveExpression> getApplicableElement() {
    return AdditiveExpression.class;
  }

  public SimplifyResult simplify(AdditiveExpression expression) {
    Multimap<String, ASTElement> mapping = LinkedListMultimap.create();
    for (ASTElement term : expression.getChildren()) {
      MathExpression expr = (MathExpression) term;
      mapping.put(expr.getSignature(), term);
    }
    boolean simplified = false;
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
            expression.removeChild(astElement);
          }
        }
        simplified = true;
      }
    }

    // convert to multiplicative expression
    ASTElement newElement = expression;
    if (expression.getChildren().size() == 1) {
      newElement = expression.getChildren().get(0);
    }

    return new SimplifyResult(simplified, newElement);
  }

}
