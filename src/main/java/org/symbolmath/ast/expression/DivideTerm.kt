package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement

class DivideTerm(numerator: ASTElement, denominator: ASTElement) : BinaryTerm(numerator, denominator) {

    override fun negate(): ASTElement {
        left.negate()
        return this;
    }

    override fun toString(): String {
        return "$left/$right"
    }

}