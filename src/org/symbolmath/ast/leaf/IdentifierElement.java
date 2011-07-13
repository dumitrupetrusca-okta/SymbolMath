package org.symbolmath.ast.leaf;

import org.symbolmath.scanner.Token;

public class IdentifierElement extends LeafElement {
  private String name;

  public IdentifierElement(Token token) {
    this.name = (String) token.getValue();
  }

  @Override
  public String toString() {
    return name;
  }
}
