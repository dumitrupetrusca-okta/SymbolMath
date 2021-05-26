package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement
import java.lang.StringBuilder

class AdditiveTerm(terms: MutableList<ASTElement>) : Term(terms) {

    override val signature: String
        get() {
            val s = StringBuilder()
            for (i in terms.indices) {
                s.append(terms[i])
                s.append(if (i != terms.size - 1) " + " else "")
            }
            return s.toString()
        }

    override fun toString(): String {
        return if (children.size > 1) {
            "($signature)"
        } else {
            signature
        }
    }

    override fun negate(): ASTElement {
        children.forEach { it.negate() }
        return this
    }

}