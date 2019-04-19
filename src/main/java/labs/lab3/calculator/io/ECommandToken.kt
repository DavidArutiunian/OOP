package labs.lab3.calculator.io

enum class ECommandToken(private val token: String) {
    VAR("var"),
    LET("let"),
    FN("fn"),
    PRINT("print"),
    PRINTVARS("printvars"),
    PRINTFNS("printfns");

    override fun toString(): String {
        return token
    }
}
