package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.AdditiveTerm

class AddZeroSimplifier : ExpressionSimplifier<AdditiveTerm> {

    override fun getApplicableElement(): Class<AdditiveTerm> {
        return AdditiveTerm::class.java
    }

    override fun simplify(expression: AdditiveTerm): ASTElement {
        val terms = expression.children
            .filter { !it.isZero() }
            .toMutableList()
        return AdditiveTerm(terms)
    }

}