package org.symbolmath.simplifier

import org.symbolmath.ast.ASTElement
import org.symbolmath.parser.Parser

class Simplifier(private var tree: ASTElement) {
    constructor(expression: String) : this(Parser(expression, true).parse())

    fun simplify(): ASTElement {
        var result: SimplifyResult
        while (simplify(tree).also { result = it }.simplified) {
            tree = result.newElement
            println(tree)
        }
        return tree
    }

    private fun simplify(root: ASTElement): SimplifyResult {
        var element = root
        var simplified = false

        for (child in element.children) {
            if (simplify(child).simplified) {
                simplified = true
            }
        }

        val parent = element.parent

        for (simplifier in SIMPLIFIERS) {
            if (element.javaClass.isAssignableFrom(simplifier.getApplicableElement())) {
                val simplifiedElement = simplifier.simplify(element)
                if (!simplifiedElement.equals(element)) {
                    simplified = true
                    if (parent != null) {
                        parent.replaceChild(element, simplifiedElement)
                    } else {
                        element = simplifiedElement
                    }
                }
            }
        }

        return SimplifyResult(simplified, element)
    }

    companion object {
        private val SIMPLIFIERS = arrayOf(
            DivSimplifier() as ExpressionSimplifier<ASTElement>,
            MulCollapseSimplifier() as ExpressionSimplifier<ASTElement>,
            MulToPowSimplifier() as ExpressionSimplifier<ASTElement>,
            NegSimplifier() as ExpressionSimplifier<ASTElement>,
            AddCollapseSimplifier() as ExpressionSimplifier<ASTElement>,
            AddSimplifier() as ExpressionSimplifier<ASTElement>,
            AddZeroSimplifier() as ExpressionSimplifier<ASTElement>,
            PowerCollapseSimplifier() as ExpressionSimplifier<ASTElement>
        )
    }

    data class SimplifyResult(val simplified: Boolean, val newElement: ASTElement)
}