package org.symbolmath.parser;

import org.symbolmath.exception.ParseException;
import org.symbolmath.scanner.*;
import org.symbolmath.ast.*;
import org.symbolmath.ast.leaf.*;
import org.symbolmath.ast.operation.*;

import java.util.List;

import static org.symbolmath.scanner.TokenType.*;

/*
    E --> T {( "+" | "-" ) T}
    T --> F {( "*" | "/" ) F}
    F --> P ["^" F]
    P --> v | "(" E ")" | "-" T
*/
public class Parser {
  private Scanner scanner;
  private Token currentToken;

  public Parser(String text, boolean ignoreWhitespace) {
    scanner = new Scanner(text, ignoreWhitespace);
    consume();
  }

  /**
   Eparser is
      var t : Tree
      t := E
      expect( end )
      return t
   */
  public ASTElement parse() {
    ASTElement expression = parseAdditiveExpression();
    match(EOF, "End of expression expected.");
    setParents(expression, null);
    return expression;
  }

  private void setParents(ASTElement expression, ASTElement parent) {
    expression.setParent(parent);
    for (ASTElement child : expression.getChildren()) {
      setParents(child, expression);
    }
  }

  /*
E is
   var t : Tree
   t := T
   while next = "+" or next = "-"
      const op := binary(next)
      consume
      const t1 := T
      t := mkNode( op, t, t1 )
   return t
   */
  private ASTElement parseAdditiveExpression() {
    ASTElement expression = parseMultiplicativeExpression();
    TokenType tokenType = currentToken.getType();
    while (tokenType == PLUS || tokenType == MINUS) {
      consume();
      ASTElement expression2 = parseMultiplicativeExpression();
      if (tokenType == PLUS) {
        expression = new AddOperation(expression, expression2);
      } else {
        expression = new SubtractOperation(expression, expression2);
      }
      tokenType = currentToken.getType();
    }
    return expression;
  }

  /*
T is
   var t : Tree
   t := F
   while next = "*" or next = "/"
      const op := binary(next)
      consume
      const t1 := F
      t := mkNode( op, t, t1 )
   return t
   */
  private ASTElement parseMultiplicativeExpression() {
    ASTElement expression = parsePowerExpression();
    TokenType tokenType = currentToken.getType();
    while (tokenType == TIMES || tokenType == DIVISION) {
      consume();
      ASTElement expression2 = parsePowerExpression();
      if (tokenType == TIMES) {
        expression = new MultiplyOperation(expression, expression2);
      } else {
        expression = new DivideOperation(expression, expression2);
      }
      tokenType = currentToken.getType();
    }
    return expression;
  }

  /*
F is
   var t : Tree
   t := P
   if next = "^"
        consume
        const t1 := F
        return mkNode( binary("^"), t, t1)
   else
        return t
   */
  private ASTElement parsePowerExpression() {
    ASTElement expression = parseParensAndUnaryExpression();
    TokenType tokenType = currentToken.getType();
    if (tokenType == POWER) {
      consume();
      ASTElement expression2 = parsePowerExpression();
      return new PowerOperation(expression, expression2);
    } else {
      return expression;
    }
  }

  /*
P is
   var t : Tree
   if next is a v
        t := mkLeaf( next )
        consume
        return t
   else if next = "("
        consume
        t := E
        expect( ")" )
        return t
   else if next = "-"
        consume
        t := F
        return mkNode( unary("-"), t)
   else
        error
   */
  private ASTElement parseParensAndUnaryExpression() {
    TokenType tokenType = currentToken.getType();
    switch (tokenType) {
      case IDENTIFIER: {
        IdentifierElement identifierElement = new IdentifierElement(currentToken);
        consume();
        return identifierElement;
      }
      case INTEGER: {
        IntegerElement integerElement = new IntegerElement(currentToken);
        consume();
        return integerElement;
      }
      case LPAREN:{
        consume();
        ASTElement expression = parseAdditiveExpression();
        match(RPAREN, ") expected");
        consume();
        return expression;
      }
      case MINUS: {
        ASTElement expression = parsePowerExpression();
        return new NegativeOperation(expression);
      }
    }

    return null;  //To change body of created methods use File | Settings | File Templates.
  }

  private void match(TokenType tokenType, String errorMessage) {
    if (currentToken.getType() != tokenType) {
      throw new ParseException(errorMessage);
    }
  }

  private void consume() {
    currentToken = scanner.nextToken();
  }
}
