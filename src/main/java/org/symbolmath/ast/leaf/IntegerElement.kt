package org.symbolmath.ast.leaf

class IntegerElement(val value: Int) : LeafElement() {
    override fun toString(): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IntegerElement

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }


}