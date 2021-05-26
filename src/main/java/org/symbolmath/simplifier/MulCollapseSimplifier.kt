package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.MultiplicativeTerm
import org.symbolmath.ast.expression.MultiplicativeTerm.Companion.create

class MulCollapseSimplifier : ExpressionSimplifier<MultiplicativeTerm> {
    override fun getApplicableElement(): Class<*> {
        return MultiplicativeTerm::class.java
    }

    override fun simplify(expression: MultiplicativeTerm): ASTElement {
        val terms: MutableList<ASTElement> = ArrayList()
        collectTerms(expression, terms)
        val multiplier = collectMultiplier(expression)
        terms.sortBy { Order.numberFirstOrder(it) }
        terms.sortBy { Order.alphabeticalOrder(it) }
        val create = create(multiplier, terms)
        return create
    }

    private fun collectTerms(expression: ASTElement, terms: MutableList<ASTElement>) {
        for (c in expression.children) {
            if (c is MultiplicativeTerm) {
                collectTerms(c, terms)
            } else {
                terms.add(c)
            }
        }
    }

    private fun collectMultiplier(term: MultiplicativeTerm): Int {
        var multiplier: Int = term.multiplier
        for (c in term.children) {
            if (c is MultiplicativeTerm) {
                multiplier *= collectMultiplier(c)
            }
        }
        return multiplier
    }
}