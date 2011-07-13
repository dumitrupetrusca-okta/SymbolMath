package org.symbolmath.ast.leaf;

import org.symbolmath.scanner.Token;

public class IntegerElement extends LeafElement {
  private Integer value;

  public IntegerElement(Token token) {
    this.value = (Integer) token.getValue();
  }

  @Override
  public String toString() {
    return value.toString();
  }

  public int getValue() {
    return value;
  }
}
