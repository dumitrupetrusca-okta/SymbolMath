package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.Term

interface ExpressionTransformer {
    fun canTransform(expression: ASTElement): Boolean
    fun transform(expression: ASTElement): Term
}