package org.symbolmath.simplifier;

import org.symbolmath.ast.ASTElement;

public abstract class ExpressionSimplifier<T extends ASTElement> {

  public abstract Class<T> getApplicableElement();

  public abstract SimplifyResult simplify(T element);

}
