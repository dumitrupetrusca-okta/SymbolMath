package org.symbolmath;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.parser.Parser;
import org.symbolmath.scanner.Scanner;
import org.symbolmath.simplifier.Simplifier;
import org.symbolmath.transformer.Transformer;

public class Example {

  public static void main(String[] args) {
    String expression = "2*x*y + 3*x*y + 4*x^2 + 2*x^2";
    Scanner scanner = new Scanner(expression, true);
    while (scanner.hasTokens()) {
      System.out.println(scanner.nextToken());
    }
    System.out.println();
    ASTElement tree = new Parser(expression, true).parse();
    System.out.println(tree);
    ASTElement transformedTree = new Transformer(tree).transform();
    System.out.println(transformedTree);
    ASTElement simplifiedTree = new Simplifier(transformedTree).simplify();
    System.out.println(simplifiedTree);
  }

}
