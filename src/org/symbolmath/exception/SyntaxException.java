package org.symbolmath.exception;

public class SyntaxException extends ParseException {

  public SyntaxException(String message, String text, int index) {
    super(getError(message, text, index));
  }

  private static String getError(String message, String text, int index) {
    return message + "\n" + text + "\n" + spaces(index) + "^";
  }

  private static String spaces(int n) {
    StringBuilder f = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      f.append(' ');
    }
    return f.toString();
  }

}
