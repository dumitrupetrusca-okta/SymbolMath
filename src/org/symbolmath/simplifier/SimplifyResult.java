package org.symbolmath.simplifier;

import org.symbolmath.ast.ASTElement;

public class SimplifyResult {
  public final boolean simplified;
  public final ASTElement newElement;

  public SimplifyResult(boolean simplified) {
    this.simplified = simplified;
    this.newElement = null;
  }

  public SimplifyResult(boolean simplified, ASTElement newElement) {
    this.simplified = simplified;
    this.newElement = newElement;
  }
}
