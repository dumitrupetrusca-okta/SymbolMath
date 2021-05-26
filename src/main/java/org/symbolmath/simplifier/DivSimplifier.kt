package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.MultiplicativeTerm
import org.symbolmath.ast.expression.DivideTerm

class DivSimplifier : ExpressionSimplifier<DivideTerm> {

    override fun getApplicableElement(): Class<DivideTerm> {
        return DivideTerm::class.java
    }

    override fun simplify(expression: DivideTerm): ASTElement = with(expression) {
        if (left is MultiplicativeTerm && right is MultiplicativeTerm) {
            val nominator = left as MultiplicativeTerm
            val denominator = right as MultiplicativeTerm

            val gcd = gcd(nominator.multiplier, denominator.multiplier)
            nominator.multiplier /= gcd
            denominator.multiplier /= gcd

            if (denominator.multiplier == 1 && denominator.children.isEmpty()) {
                return@with nominator
            }
        }
        expression
    }

    fun gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)

}