package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.NegativeTerm

class NegSimplifier : ExpressionSimplifier<NegativeTerm> {

    override fun getApplicableElement(): Class<NegativeTerm> {
        return NegativeTerm::class.java
    }

    override fun simplify(expression: NegativeTerm): ASTElement {
        return expression.children[0].negate()
    }
}