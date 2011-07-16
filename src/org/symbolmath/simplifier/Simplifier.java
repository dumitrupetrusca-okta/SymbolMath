package org.symbolmath.simplifier;

import com.sun.deploy.panel.ITreeNode;
import org.symbolmath.ast.expression.AdditiveExpression;
import org.symbolmath.parser.Parser;
import org.symbolmath.ast.*;
import org.symbolmath.scanner.Scanner;
import org.symbolmath.transformer.Transformer;

import java.util.List;

public class Simplifier {
  private static ExpressionSimplifier[] simplifiers = {
      new AdditiveExpressionSimplifier()
  };

  private ASTElement tree;

  public Simplifier(ASTElement tree) {
    this.tree = tree;
  }

  public Simplifier(String expression) {
    this(new Parser(expression, true).parse());
  }

  public ASTElement simplify() {
    SimplifyResult result;
    while ((result = simplify(tree)).simplified) {
      tree = result.newElement;
    }
    return tree;
  }

  private SimplifyResult simplify(ASTElement element) {
    boolean simplified = false;

    for (ExpressionSimplifier simplifier : simplifiers) {
      if (element.getClass().isAssignableFrom(simplifier.getApplicableElement())) {
        SimplifyResult result = simplifier.simplify(element);
        if (result.simplified) {
          simplified = true;
          if (result.newElement != null) {
            ASTElement parent = element.getParent();
            if (parent != null) {
              parent.replaceChild(element, result.newElement);
            } else {
              element = result.newElement;
            }
          }
        }
      }
    }

    if (!simplified) {
      List<? extends ASTElement> children = element.getChildren();
      for (ASTElement child : children) {
        SimplifyResult result = simplify(child);
        if (result.simplified) {
          simplified = true;
        }
      }
    }

    return new SimplifyResult(simplified, element);
  }

}
