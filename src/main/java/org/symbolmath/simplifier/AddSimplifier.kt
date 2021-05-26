package org.symbolmath.simplifier

import com.google.common.collect.LinkedListMultimap
import com.google.common.collect.Multimap
import org.symbolmath.ast.ASTElement
import org.symbolmath.ast.expression.AdditiveTerm
import org.symbolmath.ast.expression.Term
import org.symbolmath.ast.expression.MultiplicativeTerm
import org.symbolmath.ast.expression.PowerTerm

class AddSimplifier : ExpressionSimplifier<AdditiveTerm> {
    override fun getApplicableElement(): Class<AdditiveTerm> {
        return AdditiveTerm::class.java
    }

    override fun simplify(expression: AdditiveTerm): ASTElement {
        val mapping: Multimap<String, ASTElement> = LinkedListMultimap.create()
        for (term in expression.children) {
            val expr = term as Term
            mapping.put(expr.signature, term)
        }
        for (key in mapping.keySet()) {
            val elements = mapping[key]
            if (elements.size > 1) {
                var joinedTerm: MultiplicativeTerm? = null
                for (astElement in elements) {
                    if (astElement !is MultiplicativeTerm && astElement !is PowerTerm) {
                        throw RuntimeException("Must be a MultiplicativeExpression or a PowerOperation")
                    }
                    if (joinedTerm == null) {
                        joinedTerm = astElement as MultiplicativeTerm
                    } else {
                        joinedTerm.join(astElement as MultiplicativeTerm)
                        expression.removeChild(astElement)
                    }
                }
            }
        }

        // convert to multiplicative expression
        return if (expression.children.size == 1) {
            expression.children[0]
        } else {
            expression
        }
    }
}