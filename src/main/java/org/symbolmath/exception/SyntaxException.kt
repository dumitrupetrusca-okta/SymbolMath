package org.symbolmath.exception

class SyntaxException(message: String, text: String, index: Int) : ParseException(getError(message, text, index)) {
    companion object {
        private fun getError(message: String, text: String, index: Int): String {
            return """
                $message
                $text
                ${spaces(index)}^
                """.trimIndent()
        }

        private fun spaces(n: Int): String {
            return " ".repeat(Math.max(0, n))
        }
    }
}