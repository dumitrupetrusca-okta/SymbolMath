package org.symbolmath.util

import org.symbolmath.ast.ASTElement
import java.lang.Character.*

object SymbolMathUtil {

    fun checkParents(element: ASTElement, parent: ASTElement?) {
        if (element.parent !== parent) {
            throw RuntimeException("Wrong parent")
        }
        for (child in element.children) {
            checkParents(child, element)
        }
    }

    fun isIdentifier(s: String): Boolean {
        if (!isJavaIdentifierStart(s[0])) {
            return false
        }
        for (c in s) {
            if (!isJavaIdentifierPart(c)) {
                return false
            }
        }
        return true
    }

    @JvmStatic
    fun parseAsInt(s: String): Int? {
        return try {
            s.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }

    fun parseAsFloat(s: String): Double? {
        return try {
            s.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
}