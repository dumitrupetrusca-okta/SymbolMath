package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.leaf.IntegerElement

class MultiplicativeTerm(var multiplier: Int, terms: MutableList<ASTElement> = mutableListOf()) : Term(terms) {

    fun join(term: MultiplicativeTerm) {
        multiplier += term.multiplier
    }

    override fun toString(): String {
        return if (terms.isEmpty()) {
            "" + multiplier
        } else if (multiplier == -1) {
            "-$signature";
        } else if (multiplier == 1) {
            signature;
        } else {
            "" + multiplier + signature
        }
    }

    override val signature: String
        get() {
            val s = StringBuilder()
            for (term in terms) {
                s.append(term)
            }
            return s.toString()
        }

    companion object {

        fun create(multiplier: Int, terms: List<ASTElement>): Term {
            var multiplier = multiplier
            val newTerms: MutableList<ASTElement> = ArrayList(terms.size)
            for (term in terms) {
                multiplier = process(multiplier, term, newTerms)
            }
            return MultiplicativeTerm(multiplier, newTerms)
        }

        private fun process(multiplier: Int, term: ASTElement, newTerms: MutableList<ASTElement>): Int {
            var multiplier = multiplier
            if (term is NegativeTerm) {
                multiplier = process(multiplier, term.children[0], newTerms)
                multiplier *= -1
            } else if (term is IntegerElement) {
                multiplier *= term.value
            } else {
                newTerms.add(term)
            }
            return multiplier
        }
    }

    override fun negate(): ASTElement {
        multiplier *= -1
        return this
    }

    override fun isZero(): Boolean {
        return multiplier == 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as MultiplicativeTerm

        if (multiplier != other.multiplier) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + multiplier
        return result
    }


}