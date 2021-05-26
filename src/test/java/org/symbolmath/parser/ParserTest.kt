package org.symbolmath.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.symbolmath.ast.expression.NegativeTerm
import org.symbolmath.ast.leaf.IdentifierElement
import org.symbolmath.ast.leaf.IntegerElement

class ParserTest {
    @Test
    fun testWorksWithEmptyInput() {
        Parser("", true).parse()
    }

    @Test
    fun testInt() {
        val element = Parser("1", true).parse()
        Assertions.assertTrue(element is IntegerElement)
    }

    @Test
    fun testPositiveInt() {
        val element = Parser("+1", true).parse()
        Assertions.assertTrue(element is IntegerElement)
    }

    @Test
    fun testNegativeInt() {
        val element = Parser("-1", true).parse()
        Assertions.assertTrue(element is NegativeTerm)
        Assertions.assertTrue(element.children[0] is IntegerElement)
    }

    @Test
    fun testVariable() {
        val element = Parser("x", true).parse()
        Assertions.assertTrue(element is IdentifierElement)
    }

    @Test
    fun testPositiveVariable() {
        val element = Parser("+x", true).parse()
        Assertions.assertTrue(element is IdentifierElement)
    }

    @Test
    fun testNegativeVariable() {
        val element = Parser("-x", true).parse()
        Assertions.assertTrue(element is NegativeTerm)
        Assertions.assertTrue(element.children[0] is IdentifierElement)
    }

    @Test
    fun testIntInParens() {
        val element = Parser("(1)", true).parse()
        Assertions.assertTrue(element is IntegerElement)
    }

    @Test
    fun testNegativeIntInParens() {
        val element = Parser("-(1)", true).parse()
        Assertions.assertTrue(element is NegativeTerm)
    }

    @Test
    fun testVariableInParens() {
        val element = Parser("(x)", true).parse()
        Assertions.assertTrue(element is IdentifierElement)
    }

    @Test
    fun testNegativeVariableInParens() {
        val element = Parser("-(x)", true).parse()
        Assertions.assertTrue(element is NegativeTerm)
    }
}