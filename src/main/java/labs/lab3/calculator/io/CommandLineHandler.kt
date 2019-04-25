package labs.lab3.calculator.io

import labs.lab3.calculator.calculator.Operator
import labs.lab3.calculator.exceptions.SyntaxException
import java.util.*

class CommandLineHandler(private val evaluator: IEvaluator, private val sc: Scanner) {
    fun eval(token: String) = when (token) {
        ECommandToken.VAR.toString() -> handleVarToken(evaluator)
        ECommandToken.LET.toString() -> handleLetToken(evaluator)
        ECommandToken.FN.toString() -> handleFnToken(evaluator)
        ECommandToken.PRINT.toString() -> handlePrintToken(evaluator)
        ECommandToken.PRINTVARS.toString() -> handlePrintvarsToken(evaluator)
        ECommandToken.PRINTFNS.toString() -> handlePrintfnsToken(evaluator)
        else -> throw SyntaxException("Invalid or unexpected token")
    }

    private fun handleVarToken(evaluator: IEvaluator) {
        evaluator.setVar(sc.next())
    }

    private fun handleLetToken(evaluator: IEvaluator) {
        val tokens = sc.nextLine().split('=')
        evaluator.setVarValue(tokens.first().trim(), tokens.last().trim())
    }

    private fun handleFnToken(evaluator: IEvaluator) {
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
            left != null && right != null && op != null -> evaluator.setFun(ident.trim(), left.trim(), op, right.trim())
            else -> evaluator.setFun(ident.trim(), left!!.trim())
        }
    }

    private fun handlePrintToken(evaluator: IEvaluator) {
        println("%.2f".format(evaluator.getValue(sc.next())))
    }

    private fun handlePrintvarsToken(evaluator: IEvaluator) {
        println(evaluator.getVars().map { it.key to "%.2f".format(it.value) })
    }

    private fun handlePrintfnsToken(evaluator: IEvaluator) {
        println(evaluator.getFns().map { it.key to "%.2f".format(it.value.value) })
    }
}
