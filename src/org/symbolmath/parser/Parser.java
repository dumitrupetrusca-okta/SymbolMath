package org.symbolmath.parser;

import org.symbolmath.ast.ASTElement;
import org.symbolmath.ast.leaf.IdentifierElement;
import org.symbolmath.ast.leaf.IntegerElement;
import org.symbolmath.ast.operation.AddOperation;
import org.symbolmath.ast.operation.DivideOperation;
import org.symbolmath.ast.operation.MultiplyOperation;
import org.symbolmath.ast.operation.NegativeOperation;
import org.symbolmath.ast.operation.PowerOperation;
import org.symbolmath.ast.operation.SubtractOperation;
import org.symbolmath.exception.ParseException;
import org.symbolmath.scanner.Scanner;
import org.symbolmath.scanner.Token;
import org.symbolmath.scanner.TokenType;

import static org.symbolmath.scanner.TokenType.*;

/**
 * This is the formal grammar of the language:
 * <pre>
 * Expression         --> MultiplicativeTerm {( "+" | "-" ) MultiplicativeTerm}
 * MultiplicativeTerm --> PowerTerm {( "*" | "/" ) PowerTerm}
 * PowerTerm          --> ParensAndUnaryTerm ["^" PowerTerm]
 * ParensAndUnaryTerm --> ValueTerm | "(" Expression ")" | "-" PowerTerm | "+" PowerTerm
 * </pre>
 */
public class Parser {
  private Scanner scanner;
  private Token currentToken;

  public Parser(String text, boolean ignoreWhitespace) {
    scanner = new Scanner(text, ignoreWhitespace);
    consume();
  }

  public ASTElement parse() {
    ASTElement expression = parseAdditiveExpression();
    match(EOF, "End of expression expected.");
    if (expression != null) {
      setParents(expression, null);
    }
    return expression;
  }

  private void setParents(ASTElement expression, ASTElement parent) {
    expression.setParent(parent);
    for (ASTElement child : expression.getChildren()) {
      setParents(child, expression);
    }
  }

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
      case LPAREN: {
        consume();
        ASTElement expression = parseAdditiveExpression();
        match(RPAREN, ") expected");
        consume();
        return expression;
      }
      case MINUS: {
        consume();
        ASTElement expression = parsePowerExpression();
        return new NegativeOperation(expression);
      }
      case PLUS: {
        consume();
        ASTElement expression = parsePowerExpression();
        return expression;
      }
    }

    return null;
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
