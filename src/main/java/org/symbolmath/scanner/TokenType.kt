package org.symbolmath.scanner

enum class TokenType {
    PLUS("+"), MINUS("-"), TIMES("*"), DIVISION("/"), POWER("^"),
    LPAREN("("), RPAREN(")"),
    EQUALS("="),
    INTEGER, FLOAT, IDENTIFIER, WHITESPACE,
    EOF;

    var text: String? = null
        private set

    constructor()

    constructor(text: String) {
        this.text = text
    }
}