package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.AdditiveTerm

class AddCollapseSimplifier : ExpressionSimplifier<AdditiveTerm> {

    override fun getApplicableElement(): Class<*> {
        return AdditiveTerm::class.java
    }

    override fun simplify(expression: AdditiveTerm): ASTElement {
        val terms: MutableList<ASTElement> = ArrayList()
        collectTerms(expression, terms)
        return AdditiveTerm(terms)
    }

    private fun collectTerms(expression: ASTElement, terms: MutableList<ASTElement>) {
        for (c in expression.children) {
            if (c is AdditiveTerm) {
                collectTerms(c, terms)
            } else {
                terms.add(c)
            }
        }
    }
}