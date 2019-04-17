package labs.lab3.calculator

import labs.lab3.calculator.exceptions.ReferenceException
import labs.lab3.calculator.exceptions.SyntaxException
import org.apache.commons.lang3.math.NumberUtils

class Calculator {
    private val vars = HashMap<String, Double>()
    private val fns = LinkedHashMap<String, Function>()

    fun setVar(name: String) = when {
        !isNameCorrect(name) -> throw SyntaxException("Invalid or unexpected token")
        isReservedName(name) -> throw SyntaxException("Identifier '$name' has already been declared")
        else -> vars[name] = Double.NaN
    }

    fun setVarValue(name: String, value: String) {
        when {
            value.isEmpty() -> throw SyntaxException("Unexpected token")
            !vars.containsKey(name) -> setVar(name)
        }
        when {
            NumberUtils.isCreatable(value) -> vars[name] = NumberUtils.toDouble(value)
            else -> vars[name] = getValue(value)
        }
        val calculated = HashSet<String>()
        traverseAndCalcFunsValues(name, fns, calculated)
//        traverseAndCalcFunsValues(name, getReversedMap(fns), calculated)
    }

    fun setFun(ident: String, `var`: String) = when {
        isReservedName(ident) -> throw SyntaxException("Identifier '$ident' has already been declared")
        !isNameCorrect(ident) -> throw SyntaxException("Invalid or unexpected token")
        !vars.containsKey(`var`) -> throw ReferenceException("'$`var`' is not defined")
        else -> {
            fns[ident] = Function(`var`)
            setFunValue(fns[ident]!!)
        }
    }

    fun setFun(ident: String, left: String, op: Operator, right: String) {
        val conditions = booleanArrayOf(
            !isReservedName(ident),
            isNameCorrect(ident),
            isNameCorrect(left),
            isNameCorrect(right)
        )
        if (conditions.any { !it }) {
            throw SyntaxException("Invalid or unexpected token")
        }
        val fn = Function(left, right, op)
        fns[ident] = fn
        setFunValue(fn)
    }

    fun getVars() = mapOf(*vars.map { Pair(it.key, it.value) }.toTypedArray())

    fun getFns() = mapOf(*fns.map { Pair(it.key, it.value) }.toTypedArray())

    fun getValue(name: String) = when {
        fns.containsKey(name) -> fns[name]!!.value
        vars.containsKey(name) -> vars[name]!!
        else -> Double.NaN
    }

    private fun setFunValue(fn: Function) = when {
        fn.op == null -> fn.value = getValue(fn.left)
        else -> calcFunValue(fn)
    }

    private fun calcFunValue(fn: Function) {
        val left = getValue(fn.left)
        val right = getValue(fn.right!!)
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

    private fun <K, V> getReversedMap(map: Map<K, V>) = map.entries.reversed().map { it.key to it.value }.toMap()

    private fun traverseAndCalcFunsValues(name: String, fns: Map<String, Function>, calculated: HashSet<String>) {
        for (fn in fns) {
            val conditions = booleanArrayOf(
                fn.value.left == name,
                fn.value.right == name,
                calculated.contains(fn.value.left),
                calculated.contains(fn.value.right)
            )
            if (conditions.any { it }) {
                setFunValue(fns.getValue(fn.key))
                calculated.add(fn.key)
            }
        }
    }

    private fun isNameCorrect(name: String) = name.isNotEmpty() && name.first().isLetter() && name.all { it.isLetterOrDigit() }

    private fun isReservedName(name: String) = vars.containsKey(name) || fns.containsKey(name)
}
