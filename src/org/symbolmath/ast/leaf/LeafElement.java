package org.symbolmath.ast.leaf;

import org.symbolmath.ast.ASTElement;

import java.util.List;

public abstract class LeafElement extends ASTElement {

  public final void replaceChild(ASTElement element, ASTElement newElement) {
    throw new UnsupportedOperationException("replaceChild() not supported for leaf elements.");
  }

  @Override
  public List<ASTElement> getChildren() {
    return NO_CHILDREN;
  }
}
