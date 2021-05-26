The intention of the SymbolMath project is to produce a Java library for symbolic math.
Expression simplification and equation solving are just two examples of possible applications.

To see the library in action run "org.symbolmath.Example".

At the moment the source contains:

    1. A simplistic (but working) scanner
    2. A parser that understands the four basic operations and the power operation
    3. A strongly typed AST (Abstract Syntax Tree) representing the parsed expression
    3. A transformer that converts binary operators into n-ary operators and extracts
       constant multipliers (this makes simplification easier)
    4. A simplifier that can simplify additive terms (e.g. 2x + 3x -> 5x)

Contributors are welcome!