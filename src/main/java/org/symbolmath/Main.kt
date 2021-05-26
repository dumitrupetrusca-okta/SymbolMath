package org.symbolmath

import org.symbolmath.parser.Parser
import org.symbolmath.simplifier.Simplifier

fun main() {
//    simplify("1 + 1")
    simplify("x^2")
//    simplify("(2*x*y + y*2*x)/(1+1)")
//    simplify("(2*x*y + y*2*x)/(4*x^2 - 2*x^1*x + 1 - 1)")
}

private fun simplify(expr: String) {
    val expression = expr
    val tree = Parser(expression, true).parse()
    val simplifiedTree = Simplifier(tree).simplify()
    println("$expression  ==>  $simplifiedTree")
}