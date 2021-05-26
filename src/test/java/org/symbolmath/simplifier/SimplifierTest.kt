package org.symbolmath.simplifier

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimplifierTest {
    @Test
    fun testAddingNumbers() {
        simplify("1 + 1", "2")
    }

    @Test
    fun testAddingSignedNumbers() {
        simplify("(-1) - (-(2-1))", "0")
    }

    @Test
    fun testMultiplyingNumbers() {
        simplify("3 * 4", "12")
    }

    @Test
    fun testMultiplyingSignedNumbers() {
        simplify("3 * (-4)", "-12")
    }

    @Test
    fun testDividingNumbers() {
        simplify("4 / 2", "2")
    }

    @Test
    fun testDividingSignedNumbers1() {
        simplify("4 / (-2)", "-2")
    }

    @Test
    fun testDividingSignedNumbers2() {
        simplify("-4 / -2", "2")
    }

    private fun simplify(expression: String, result: String) {
        Assertions.assertEquals(result, Simplifier(expression).simplify().toString())
    }
}