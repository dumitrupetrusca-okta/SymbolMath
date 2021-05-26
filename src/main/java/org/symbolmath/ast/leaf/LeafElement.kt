package org.symbolmath.ast.leaf

import org.symbolmath.ast.ASTElement

abstract class LeafElement : ASTElement() {

    override val children: List<ASTElement>
        get() = listOf()

    override fun replaceChild(element: ASTElement, newElement: ASTElement) {
        throw UnsupportedOperationException("replaceChild() not supported for leaf elements.")
    }

    override fun negate(): ASTElement {
        TODO("Not yet implemented")
    }

}