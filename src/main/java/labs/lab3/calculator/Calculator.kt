package labs.lab3.calculator

import labs.lab3.calculator.exceptions.ReferenceException
import labs.lab3.calculator.exceptions.SyntaxException

class Calculator {
    private val vars = HashMap<String, Double>()
    private val fns = HashMap<String, Function>()

    fun setVar(name: String) = when {
        !isNameCorrect(name) -> throw SyntaxException("Invalid or unexpected token")
        isReservedName(name) -> throw SyntaxException("Identifier '$name' has already been declared")
        else -> vars[name] = Double.NaN
    }

    fun setFun(ident: String, `var`: String) = when {
        isReservedName(ident) -> throw SyntaxException("Identifier '$ident' has already been declared")
        !isNameCorrect(ident) -> throw SyntaxException("Invalid or unexpected token")
        !vars.containsKey(`var`) -> throw ReferenceException("'$`var`' is not defined")
        else -> {
            fns[ident] = Function(`var`)
            setFunValue(ident)
        }
    }

    private fun setFunValue(name: String) {
        if (!fns.containsKey(name)) {
            return
        }
        val fn = fns[name]!!
        when {
            fn.op == null -> fn.value = getValue(fn.left)
            else -> TODO("calculate function value")
        }
    }

    fun getVars() = mapOf(*vars.map { Pair(it.key, it.value) }.toTypedArray())

    fun getFns() = mapOf(*fns.map { Pair(it.key, it.value) }.toTypedArray())

    fun getValue(name: String): Double = when {
        fns.containsKey(name) -> fns[name]!!.value
        vars.containsKey(name) -> vars[name]!!
        else -> Double.NaN
    }

    private fun isNameCorrect(name: String) = name.isNotEmpty() && name.first().isLetter() && name.all { it.isLetterOrDigit() }

    private fun isReservedName(name: String) = vars.containsKey(name) || fns.containsKey(name)
}
