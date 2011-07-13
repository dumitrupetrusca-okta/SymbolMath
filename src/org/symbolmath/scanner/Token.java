package org.symbolmath.scanner;

public class Token {
  private final TokenType type;
  private Object value;

  public Token(TokenType type, Object value) {
    this.type = type;
    this.value = value;
  }

  public String toString() {
    return type + (value == null ? "" : "(" + value + ")");
  }

  public TokenType getType() {
    return type;
  }

  public Object getValue() {
    return value;
  }
}
