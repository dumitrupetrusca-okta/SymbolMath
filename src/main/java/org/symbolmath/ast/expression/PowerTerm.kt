package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement

class PowerTerm(left: ASTElement, right: ASTElement) : BinaryTerm(left, right) {
    override fun negate(): ASTElement {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        if (right is MultiplicativeTerm &&
            (right as MultiplicativeTerm).multiplier == 1 &&
            right.children.isEmpty()) {
            return "$left";
        }
        return "$left^$right"
    }
}