package org.symbolmath.simplifier;

import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.parser.Parser;
import org.symbolmath.ast.*;
import org.symbolmath.scanner.Scanner;
import org.symbolmath.transformer.Transformer;

public class Simplifier {
  private ASTElement tree;

  public Simplifier(ASTElement tree) {
    this.tree = tree;
  }

  public ASTElement simplify() {
    return simplify(tree);
  }

  private ASTElement simplify(ASTElement element) {
    if (element instanceof AdditiveExpression) {
      ASTElement astElement = simplify((AdditiveExpression) element);
      if (astElement != element) {

      }
    }
    return element;
  }

  private ASTElement simplify(AdditiveExpression expression) {
    return expression.tryMerging();
  }

}
