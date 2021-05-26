package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.AdditiveTerm
import org.symbolmath.ast.expression.MultiplicativeTerm
import org.symbolmath.ast.expression.PowerTerm

class PowerCollapseSimplifier : ExpressionSimplifier<PowerTerm> {
    override fun getApplicableElement(): Class<PowerTerm> {
        return PowerTerm::class.java
    }

    override fun simplify(expression: PowerTerm): ASTElement {
        val base = expression.left
        if (base is MultiplicativeTerm &&
            base.multiplier == 1 &&
            base.children.size == 1 &&
            base.children[0] is PowerTerm) {

            val nestedPower = base.children[0] as PowerTerm
            val p1 = nestedPower.right
            val p2 = expression.right
            val powerOperation = PowerTerm(nestedPower.left, MultiplicativeTerm(1, mutableListOf(p1, p2)))
            return powerOperation
        }

        return expression
    }
}