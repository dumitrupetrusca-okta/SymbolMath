package org.symbolmath.parser

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.AdditiveTerm
import org.symbolmath.ast.expression.MultiplicativeTerm
import org.symbolmath.ast.leaf.IdentifierElement
import org.symbolmath.ast.expression.DivideTerm
import org.symbolmath.ast.expression.NegativeTerm
import org.symbolmath.ast.expression.PowerTerm
import org.symbolmath.exception.ParseException
import org.symbolmath.scanner.Scanner
import org.symbolmath.scanner.Token
import org.symbolmath.scanner.TokenType
import org.symbolmath.scanner.TokenType.*

/**
 * This is the formal grammar of the language:
 * <pre>
 * Expression         --> MultiplicativeTerm {( "+" | "-" ) MultiplicativeTerm}
 * MultiplicativeTerm --> PowerTerm {( "*" | "/" ) PowerTerm}
 * PowerTerm          --> ParensAndUnaryTerm ["^" PowerTerm]
 * ParensAndUnaryTerm --> ValueTerm | "(" Expression ")" | "-" PowerTerm | "+" PowerTerm
</pre> *
 */
class Parser(text: String, ignoreWhitespace: Boolean) {
    private val scanner = Scanner(text, ignoreWhitespace)
    private var currentToken: Token? = null

    init {
        consume()
    }

    fun parse(): ASTElement {
        val expression = parseAdditiveExpression()
        match(EOF, "End of expression expected.")
        if (expression != null) {
            setParents(expression, null)
        }
        return expression!!
    }

    private fun setParents(expression: ASTElement, parent: ASTElement?) {
        expression.parent = parent
        for (child in expression.children) {
            setParents(child, expression)
        }
    }

    private fun parseAdditiveExpression(): ASTElement? {
        var expression = parseMultiplicativeExpression()
        var tokenType = currentToken!!.type
        while (tokenType == PLUS || tokenType == MINUS) {
            consume()
            val expression2 = parseMultiplicativeExpression()
            expression =
                if (tokenType == PLUS) {
                    AdditiveTerm(mutableListOf(expression!!, expression2!!))
                } else {
                    AdditiveTerm(mutableListOf(expression!!, NegativeTerm(expression2!!)))
                }
            tokenType = currentToken!!.type
        }
        return expression
    }

    private fun parseMultiplicativeExpression(): ASTElement? {
        var expression = parsePowerExpression()
        var tokenType = currentToken!!.type
        while (tokenType == TIMES || tokenType == DIVISION) {
            consume()
            val expression2 = parsePowerExpression()
            expression = if (tokenType == TIMES) {
                MultiplicativeTerm(1, mutableListOf(expression!!, expression2!!))
            } else {
                DivideTerm(expression!!, expression2!!)
            }
            tokenType = currentToken!!.type
        }
        return expression
    }

    private fun parsePowerExpression(): ASTElement? {
        val expression = parseParensAndUnaryExpression()
        return if (currentToken!!.type == POWER) {
            consume()
            val expression2 = parsePowerExpression()
            PowerTerm(expression!!, expression2!!)
        } else {
            expression
        }
    }

    private fun parseParensAndUnaryExpression(): ASTElement? {
        when (currentToken!!.type) {
            IDENTIFIER -> {
                val id = IdentifierElement((currentToken!!.value as String))
                consume()
                return MultiplicativeTerm(1, mutableListOf(PowerTerm(id, MultiplicativeTerm(1))))
            }
            INTEGER -> {
                val integerElement = MultiplicativeTerm(currentToken!!.value as Int)
                consume()
                return integerElement
            }
            LPAREN -> {
                consume()
                val expression = parseAdditiveExpression()
                match(RPAREN, ") expected")
                consume()
                return expression
            }
            MINUS -> {
                consume()
                val expression = parsePowerExpression()
                return NegativeTerm(expression!!)
            }
            PLUS -> {
                consume()
                return parsePowerExpression()
            }
        }
        return null
    }

    private fun match(tokenType: TokenType, errorMessage: String) {
        if (currentToken!!.type != tokenType) {
            throw ParseException(errorMessage)
        }
    }

    private fun consume() {
        currentToken = scanner.nextToken()
    }
}