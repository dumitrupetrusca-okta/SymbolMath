package org.symbolmath.ast.expression;

import org.symbolmath.ast.ASTElement;

import java.util.List;

public abstract class MathExpression extends ASTElement {
  protected List<ASTElement> terms;

  public MathExpression(List<ASTElement> terms) {
    this.terms = terms;
    setTermParents();
  }

  private void setTermParents() {
    for (ASTElement term : terms) {
      term.setParent(this);
    }
  }

  @Override
  public void replaceChild(ASTElement element, ASTElement newElement) {
    throw new UnsupportedOperationException("not supported yet");
  }

  @Override
  public List<ASTElement> getChildren() {
    return terms;
  }

  public void removeChild(ASTElement astElement) {
    terms.remove(astElement);
  }

  public abstract String getSignature();

}
