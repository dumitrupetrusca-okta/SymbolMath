package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.MultiplicativeTerm
import org.symbolmath.ast.leaf.IdentifierElement
import org.symbolmath.ast.leaf.IntegerElement
import org.symbolmath.ast.expression.PowerTerm

class MulToPowSimplifier : ExpressionSimplifier<MultiplicativeTerm> {

    override fun getApplicableElement(): Class<MultiplicativeTerm> {
        return MultiplicativeTerm::class.java
    }

    override fun simplify(operation: MultiplicativeTerm): ASTElement {
        val powers = operation.children
            .filterIsInstance<IdentifierElement>()
            .groupBy { it.name }
            .map { toExpr(it.value) }

        if (powers.isEmpty()) {
            return operation
        }

        return MultiplicativeTerm.create(operation.multiplier, powers)
    }

    private fun toExpr(terms: List<IdentifierElement>) =
        if (terms.size > 1) {
            PowerTerm(terms[0], IntegerElement(terms.size))
        } else {
            terms[0]
        }

}