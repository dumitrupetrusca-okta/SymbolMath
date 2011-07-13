package org.symbolmath.scanner;

public enum TokenType {
  PLUS("+"),
  MINUS("-"),
  TIMES("*"),
  DIVISION("/"),
  POWER("^"),
  LPAREN("("),
  RPAREN(")"),
  EQUALS("="),
  INTEGER(),
  FLOAT(),
  IDENTIFIER(),
  WHITESPACE(),
  EOF();

  private String text;

  TokenType() {
  }

  TokenType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
