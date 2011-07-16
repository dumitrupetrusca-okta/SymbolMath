package org.symbolmath.util;

import org.symbolmath.ast.ASTElement;

public class SymbolMathUtil {

  public static void checkParents(ASTElement element, ASTElement parent) {
    if (element.getParent() != parent) {
      throw new RuntimeException("Wrong parent");
    }
    for (ASTElement child : element.getChildren()) {
      checkParents(child, element);
    }
  }

  public static boolean isIdentifier(String s) {
    if (!Character.isJavaIdentifierStart(s.charAt(0))) {
      return false;
    }
    for (int i = 1; i < s.length(); i++) {
      if (!Character.isJavaIdentifierPart(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static Integer parseAsInt(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  public static Double parseAsFloat(String s) {
    try {
      return Double.parseDouble(s);
    } catch (NumberFormatException e) {
      return null;
    }
  }

}
