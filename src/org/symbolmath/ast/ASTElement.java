package org.symbolmath.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTElement {
  public static final List<ASTElement> NO_CHILDREN = new ArrayList<ASTElement>();

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
