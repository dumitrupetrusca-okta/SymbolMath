package org.symbolmath.ast.expression

import org.symbolmath.ast.ASTElement
import org.symbolmath.exception.TransformException

abstract class BinaryTerm(var left: ASTElement, var right: ASTElement) : ASTElement() {

    override fun replaceChild(element: ASTElement, newElement: ASTElement) {
        if (element === left) {
            left = newElement
            newElement.parent = this
        } else if (element === right) {
            right = newElement
            newElement.parent = this
        } else {
            throw TransformException("No such child: $element")
        }
    }

    override val children: List<ASTElement>
        get() {
            return listOf(left, right)
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BinaryTerm

        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }


}