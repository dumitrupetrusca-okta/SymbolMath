package org.symbolmath.scanner

class Token(val type: TokenType, val value: Any?) {
    override fun toString(): String {
        return type.toString() + if (value == null) "" else "($value)"
    }
}