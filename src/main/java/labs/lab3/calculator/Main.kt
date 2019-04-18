package labs.lab3.calculator

import labs.lab3.calculator.exceptions.SyntaxException
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val calc = Calculator()
    var token: String
    while (true) {
        token = sc.next()
        try {
            when (token) {
                "var" -> calc.setVar(sc.next())
                "let" -> {
                    val tokens = sc.nextLine().split('=')
                    calc.setVarValue(tokens.first().trim(), tokens.last().trim())
                }
                "fn" -> {
                    val tokens = sc.nextLine().split('=')
                    val ident = tokens.first()
                    val operation = tokens.last().find { arrayOf('+', '-', '*', '/').contains(it) }
                    val expr = tokens.last().split('+', '-', '*', '/')
                    var left: String? = tokens.last()
                    val op: Operator? = when (operation) {
                        '+' -> Operator.ADD
                        '-' -> Operator.SUB
                        '*' -> Operator.MUL
                        '/' -> Operator.DIV
                        else -> null
                    }
                    var right: String? = null
                    if (expr.size == 2) {
                        left = expr.first()
                        right = expr.last()
                    }
                    when {
                        left != null && right != null && op != null -> calc.setFun(ident.trim(), left.trim(), op, right.trim())
                        left != null -> calc.setFun(ident.trim(), left.trim())
                        else -> throw SyntaxException("Invalid or unexpected token")
                    }
                }
                "print" -> println("%.2f".format(calc.getValue(sc.next())))
                "printvars" -> println(calc.getVars().map { it.key to "%.2f".format(it.value) })
                "printfns" -> println(calc.getFns().map { it.key to "%.2f".format(it.value.value) })
                else -> throw SyntaxException("Invalid or unexpected token")
            }

        } catch (ex: Exception) {
            print(ex.message)
        }
    }
}
