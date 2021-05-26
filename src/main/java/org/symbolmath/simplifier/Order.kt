package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.leaf.IdentifierElement
import org.symbolmath.ast.leaf.IntegerElement

object Order {
    fun numberFirstOrder(term: ASTElement): Int {
        return if (term is IntegerElement) {
            1
        } else if (term is IdentifierElement) {
            2
        } else {
            3
        }
    }

    fun alphabeticalOrder(term: ASTElement): String {
        return if (term is IdentifierElement) {
            term.name
        } else {
            ""
        }
    }
}