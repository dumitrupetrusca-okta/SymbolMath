package org.symbolmath.scanner

import org.symbolmath.exception.SyntaxException
import org.symbolmath.util.SymbolMathUtil.parseAsInt

class Scanner(private val text: String, private val ignoreWhitespace: Boolean) {
    private var index: Int = -1
    private var currentChar = 0

    private fun nextChar(): Int {
        return if (index == text.length - 1) {
            currentChar = -1
            currentChar
        } else {
            index++
            currentChar = text[index].code
            currentChar
        }
    }

    fun hasTokens(): Boolean {
        return currentChar != -1
    }

    fun nextToken(): Token {
        if (currentChar == -1) {
            return Token(TokenType.EOF, "")
        }
        if (isWhitespace(currentChar)) {
            return if (ignoreWhitespace) {
                parseWhitespace()
                nextToken()
            } else {
                parseWhitespace()
            }
        }
        for (tt in TokenType.values()) {
            val text = tt.text
            if (text != null && text.length == 1 && currentChar == text[0].code) {
                nextChar()
                return Token(tt, null)
            }
        }
        if (isIdentifierStart(currentChar)) {
            return parseIdentifier()
        }
        if (isDigit(currentChar)) {
            return parseNumber()
        }
        throw SyntaxException("Syntax error: unexpected symbol '" + currentChar.toChar() + "'", text, index)
    }

    private fun parseNumber(): Token {
        val digitText = StringBuilder()
        do {
            digitText.append(currentChar.toChar())
        } while (isDigit(nextChar()))
        return Token(TokenType.INTEGER, parseAsInt(digitText.toString()))
    }

    private fun parseWhitespace(): Token {
        val white = StringBuilder()
        do {
            white.append(currentChar.toChar())
        } while (isWhitespace(nextChar()))
        return Token(TokenType.WHITESPACE, white.toString())
    }

    private fun parseIdentifier(): Token {
        val id = StringBuilder()
        do {
            id.append(currentChar.toChar())
        } while (isIdentifierPart(nextChar()))
        return Token(TokenType.IDENTIFIER, id.toString())
    }

    private fun isDigit(c: Int): Boolean {
        return c >= '0'.code && c <= '9'.code
    }

    private fun isWhitespace(c: Int): Boolean {
        return Character.isWhitespace(c)
    }

    private fun isIdentifierPart(c: Int): Boolean {
        return Character.isJavaIdentifierPart(c)
    }

    private fun isIdentifierStart(c: Int): Boolean {
        return Character.isJavaIdentifierStart(c)
    }

    init {
        nextChar()
    }
}