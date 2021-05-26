package org.symbolmath.ast

abstract class ASTElement {
    var parent: ASTElement? = null
    abstract val children: List<ASTElement>
    abstract fun replaceChild(element: ASTElement, newElement: ASTElement)
    abstract fun negate(): ASTElement
    open fun isZero() = false
    abstract override fun toString(): String
    abstract override fun equals(other: Any?): Boolean
}