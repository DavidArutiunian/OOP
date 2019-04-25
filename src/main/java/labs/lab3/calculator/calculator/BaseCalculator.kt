package labs.lab3.calculator.calculator

import labs.lab3.calculator.exceptions.ReferenceException
import labs.lab3.calculator.exceptions.SyntaxException
import labs.lab3.calculator.io.IEvaluator
import org.apache.commons.lang3.math.NumberUtils

open class BaseCalculator : IEvaluator {
    protected val vars = HashMap<String, Double>()
    protected val fns = LinkedHashMap<String, Function>()

    override fun setVar(name: String) = when {
        !isNameCorrect(name) -> throw SyntaxException("Invalid or unexpected token")
        isReservedName(name) -> throw SyntaxException("Identifier '$name' has already been declared")
        else -> vars[name] = Double.NaN
    }

    override fun setVarValue(name: String, value: String) {
        when {
            value.isEmpty() -> throw SyntaxException("Unexpected token")
            !vars.containsKey(name) -> setVar(name)
        }
        when {
            NumberUtils.isCreatable(value) -> vars[name] = NumberUtils.toDouble(value)
            else -> vars[name] = getValue(value)
        }
    }

    override fun setFun(ident: String, `var`: String) = when {
        isReservedName(ident) -> throw SyntaxException("Identifier '$ident' has already been declared")
        !isNameCorrect(ident) -> throw SyntaxException("Invalid or unexpected token")
        !vars.containsKey(`var`) -> throw ReferenceException("'$`var`' is not defined")
        else -> fns[ident] = Function(`var`)
    }

    override fun setFun(ident: String, left: String, op: Operator, right: String) {
        val conditions = booleanArrayOf(
            !isReservedName(ident),
            isNameCorrect(ident),
            isNameCorrect(left),
            isNameCorrect(right)
        )
        if (conditions.any { !it }) {
            throw SyntaxException("Invalid or unexpected token")
        }
        fns[ident] = Function(left, right, op)
    }

    override fun getVars() = mapOf(*vars.map { Pair(it.key, it.value) }.toTypedArray())

    override fun getFns() = mapOf(*fns.map { Pair(it.key, it.value) }.toTypedArray())

    override fun getValue(name: String) = when {
        fns.containsKey(name) -> fns[name]!!.value
        vars.containsKey(name) -> vars[name]!!
        else -> Double.NaN
    }

    protected fun calcFunValue(fn: Function) {
        val left = fns[fn.left]?.value ?: vars[fn.left] ?: Double.NaN
        val right = fns[fn.right!!]?.value ?: vars[fn.right!!] ?: Double.NaN
        if (left == Double.NaN || right == Double.NaN) {
            return
        }
        var value = Double.NaN
        when {
            fn.op == Operator.ADD -> value = left + right
            fn.op == Operator.SUB -> value = left - right
            fn.op == Operator.MUL -> value = left * right
            fn.op == Operator.DIV -> value = when (right) {
                0.0 -> Double.POSITIVE_INFINITY
                else -> left / right
            }
        }
        fn.value = value
    }

    private fun isNameCorrect(name: String) = name.isNotEmpty() && name.first().isLetter() && name.all { it.isLetterOrDigit() || it == '_' }

    private fun isReservedName(name: String) = vars.containsKey(name) || fns.containsKey(name)
}
