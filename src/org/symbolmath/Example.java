package org.symbolmath;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.parser.Parser;
import org.symbolmath.simplifier.Simplifier;
import org.symbolmath.transformer.Transformer;

public class Example {

  public static void main(String[] args) {
    String expression = "(2*x*y + 3*x*y)/(+4*x^2 + 2*x^2)";
    System.out.println(expression);
    ASTElement tree = new Parser(expression, true).parse();
    ASTElement transformedTree = new Transformer(tree).transform();
    ASTElement simplifiedTree = new Simplifier(transformedTree).simplify();
    System.out.println(simplifiedTree);
  }

}
