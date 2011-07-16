package org.symbolmath.scanner;

import org.symbolmath.exception.ParseException;
import org.symbolmath.exception.SyntaxException;
import org.symbolmath.util.SymbolMathUtil;

public class Scanner {
  private boolean ignoreWhitespace;
  private String text;
  private int index;
  private int currentChar;

  public Scanner(String text, boolean ignoreWhitespace) {
    this.text = text;
    this.ignoreWhitespace = ignoreWhitespace;
    this.index = -1;
    nextChar();
  }

  private int nextChar() {
    if (index == text.length() - 1) {
      currentChar = -1;
      return currentChar;
    } else {
      index++;
      currentChar = text.charAt(index);
      return currentChar;
    }
  }

  public boolean hasTokens() {
    return currentChar != -1;
  }

  public Token nextToken() throws ParseException {
    if (currentChar == -1) {
      return new Token(TokenType.EOF, "");
    }
    if (isWhitespace(currentChar)) {
      if (ignoreWhitespace) {
        parseWhitespace();
        return nextToken();
      } else {
        return parseWhitespace();
      }
    }
    for (TokenType tt : TokenType.values()) {
      String text = tt.getText();
      if (text != null && text.length() == 1 && currentChar == text.charAt(0)) {
        nextChar();
        return new Token(tt, null);
      }
    }
    if (isIdentifierStart(currentChar)) {
      return parseIdentifier();
    }
    if (isDigit(currentChar)) {
      return parseNumber();
    }
    throw new SyntaxException("Syntax error: unexpected symbol '" + (char)currentChar + "'", text, index);
  }

  private Token parseNumber() {
    StringBuilder digitText = new StringBuilder();
    do {
      digitText.append((char)currentChar);
    } while (isDigit(nextChar()));
    return new Token(TokenType.INTEGER, SymbolMathUtil.parseAsInt(digitText.toString()));
  }

  private Token parseWhitespace() {
    StringBuilder white = new StringBuilder();
    do {
      white.append((char)currentChar);
    } while (isWhitespace(nextChar()));
    return new Token(TokenType.WHITESPACE, white.toString());
  }

  private Token parseIdentifier() {
    StringBuilder id = new StringBuilder();
    do {
      id.append((char)currentChar);
    } while (isIdentifierPart(nextChar()));
    return new Token(TokenType.IDENTIFIER, id.toString());
  }

  private boolean isDigit(int c) {
    return c >= '0' && c <= '9';
  }

  private boolean isWhitespace(int c) {
    return Character.isWhitespace(c);
  }

  private boolean isIdentifierPart(int c) {
    return Character.isJavaIdentifierPart(c);
  }

  private boolean isIdentifierStart(int c) {
    return Character.isJavaIdentifierStart(c);
  }

}
