package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement
import org.symbolmath.exception.TransformException

class NegativeTerm(private var expression: ASTElement) : ASTElement() {
    init {
        expression.parent = this
    }

    override fun negate(): ASTElement {
        return children[0];
    }

    override val children: List<ASTElement>
        get() {
            return listOf(expression)
        }

    override fun replaceChild(element: ASTElement, newElement: ASTElement) {
        if (expression === element) {
            expression = newElement
            newElement.parent = this
        } else {
            throw TransformException("No such child: $element")
        }
    }

    override fun toString(): String {
        return "(-($expression))"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NegativeTerm

        if (expression != other.expression) return false

        return true
    }

    override fun hashCode(): Int {
        return expression.hashCode()
    }


}
