package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement

interface ExpressionSimplifier<in T : ASTElement> {
    fun getApplicableElement(): Class<*>
    fun simplify(expression: T): ASTElement
}