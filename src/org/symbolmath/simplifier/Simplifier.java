package org.symbolmath.simplifier;

import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.parser.Parser;
import org.symbolmath.ast.*;
import org.symbolmath.transformer.Transformer;

public class Simplifier {
  private ASTElement tree;

  public Simplifier(ASTElement tree) {
    this.tree = tree;
  }

  private ASTElement simplify() {
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

  public static void main(String[] args) {
    // 2(x^4+x) + 3(x^4+x)
    ASTElement tree = new Parser("2*x*y + 3*x*y + 4*x^2", true).parse();
    ASTElement transformedTree = new Transformer(tree).transform();
    ASTElement simplifideTree = new Simplifier(transformedTree).simplify();
    System.out.println();
  }

}
