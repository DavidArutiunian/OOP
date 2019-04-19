package labs.lab3.calculator.io

import labs.lab3.calculator.IOperatable
import labs.lab3.calculator.Operator
import labs.lab3.calculator.exceptions.SyntaxException
import labs.lab3.calculator.operations.*
import java.util.*

typealias THandler = (operatable: IOperatable) -> Unit

class CommandLineHandler(private val sc: Scanner) : IHandler {
    private val handlers = HashMap<String, THandler>()

    init {
        handlers[ECommandToken.VAR.toString()] = { operatable -> handleVarToken(operatable) }
        handlers[ECommandToken.LET.toString()] = { operatable -> handleLetToken(operatable) }
        handlers[ECommandToken.FN.toString()] = { operatable -> handleFnToken(operatable) }
        handlers[ECommandToken.PRINT.toString()] = { operatable -> handlePrintToken(operatable) }
        handlers[ECommandToken.PRINTVARS.toString()] = { operatable -> handlePrintvarsToken(operatable) }
        handlers[ECommandToken.PRINTFNS.toString()] = { operatable -> handlePrintfnsToken(operatable) }
    }

    override fun getTokenHandler(token: String): THandler {
        if (!handlers.containsKey(token)) {
            throw SyntaxException("Invalid or unexpected token")
        }
        return handlers[token]!!
    }

    private fun handleVarToken(operatable: ISetVar) {
        operatable.setVar(sc.next())
    }

    private fun handleLetToken(operatable: ISetVarValue) {
        val tokens = sc.nextLine().split('=')
        operatable.setVarValue(tokens.first().trim(), tokens.last().trim())
    }

    private fun handleFnToken(operatable: ISetFun) {
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
            left != null && right != null && op != null -> operatable.setFun(ident.trim(), left.trim(), op, right.trim())
            else -> operatable.setFun(ident.trim(), left!!.trim())
        }
    }

    private fun handlePrintToken(operatable: IGetValue) {
        println("%.2f".format(operatable.getValue(sc.next())))
    }

    private fun handlePrintvarsToken(operatable: IGetVars) {
        println(operatable.getVars().map { it.key to "%.2f".format(it.value) })
    }

    private fun handlePrintfnsToken(operatable: IGetFns) {
        println(operatable.getFns().map { it.key to "%.2f".format(it.value.value) })
    }
}
