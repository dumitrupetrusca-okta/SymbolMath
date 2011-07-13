package org.symbolmath.ast;

import java.util.List;

public abstract class ASTElement {
  protected ASTElement parent;

  public ASTElement() {
  }

  public ASTElement getParent() {
    return parent;
  }

  public void setParent(ASTElement parent) {
    this.parent = parent;
  }

  public abstract void replaceChild(ASTElement element, ASTElement newElement);

  public abstract String toString();

  public abstract List<? extends ASTElement> getChildren();
}
