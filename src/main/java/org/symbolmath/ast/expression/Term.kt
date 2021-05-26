package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement

abstract class Term(protected var terms: MutableList<ASTElement>) : ASTElement() {
    init {
        for (term in terms) {
            term.parent = this
        }
    }

    override val children: List<ASTElement>
        get() {
            return terms
        }

    override fun replaceChild(element: ASTElement, newElement: ASTElement) {
        val i = terms.indexOf(element)
        terms[i] = newElement
        newElement.parent = this
    }

    fun removeChild(astElement: ASTElement) {
        terms.remove(astElement)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Term

        if (terms != other.terms) return false

        return true
    }

    override fun hashCode(): Int {
        return terms.hashCode()
    }

    abstract val signature: String


}