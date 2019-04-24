package labs.lab3.calculator

import labs.lab3.calculator.exceptions.ReferenceException
import labs.lab3.calculator.exceptions.SyntaxException
import org.apache.commons.lang3.math.NumberUtils
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Calculator : IOperatable {
    private val vars = HashMap<String, Double>()
    private val fns = HashMap<String, Function>()
    private val graph = SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java)

    override fun setVar(name: String) = when {
        !isNameCorrect(name) -> throw SyntaxException("Invalid or unexpected token")
        isReservedName(name) -> throw SyntaxException("Identifier '$name' has already been declared")
        else -> {
            addVarToGraph(name)
            vars[name] = Double.NaN
        }
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
        addVarToGraph(name)
        setNeighboursExpired(name)
    }

    override fun setFun(ident: String, `var`: String) {
        if (isReservedName(ident)) {
            throw SyntaxException("Identifier '$ident' has already been declared")
        }
        if (!isNameCorrect(ident)) {
            throw SyntaxException("Invalid or unexpected token")
        }
        if (!vars.containsKey(`var`)) {
            throw ReferenceException("'$`var`' is not defined")
        }
        val fn = Function(`var`)
        addFunToGraph(ident, `var`)
        addFunToMap(ident, fn)
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
        val fn = Function(left, right, op)
        addFunToGraph(ident, fn)
        addFunToMap(ident, fn)
    }

    override fun getVars() = mapOf(*vars.map { Pair(it.key, it.value) }.toTypedArray())

    override fun getFns(): Map<String, Function> {
        for (`var` in vars) {
            calcFunsValues(`var`.key)
        }
        return mapOf(*fns.map { Pair(it.key, it.value) }.toTypedArray())
    }

    override fun getValue(name: String): Double {
        if (fns.containsKey(name)) {
            calcFunsValues(name)
            return fns[name]!!.value
        }
        if (vars.containsKey(name)) {
            return vars[name]!!
        }
        return Double.NaN
    }

    private fun safeGetValues(name: String): Double {
        if (fns.containsKey(name)) {
            return fns[name]!!.value
        }
        if (vars.containsKey(name)) {
            return vars[name]!!
        }
        return Double.NaN
    }

    private fun calcFunsValues(name: String) {
        if (!fns.containsKey(name)) {
            return
        }
        val queue = ArrayDeque<String>()
        val nodes = ArrayList<String>()
        nodes.add(name)
        getOutgoingEdges(name).forEach { queue.add(it) }
        while (!queue.isEmpty()) {
            val curr = queue.first
            queue.pop()
            nodes.add(curr)
            getOutgoingEdges(curr).forEach { queue.add(it) }
        }
        for (fn in nodes.reversed()) {
            setFunValue(fns[fn]!!)
            setFunNotExpired(fns[fn]!!)
        }
    }

    private fun setFunValue(fn: Function) = when {
        fn.op == null -> fn.value = safeGetValues(fn.left)
        else -> calcFunValue(fn)
    }

    private fun calcFunValue(fn: Function) {
        val left = safeGetValues(fn.left)
        val right = safeGetValues(fn.right!!)
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

    private fun addFunToGraph(ident: String, fn: Function) {
        graph.addVertex(ident)
        graph.addEdge(ident, fn.left)
        graph.addEdge(ident, fn.right)
    }

    private fun addFunToGraph(ident: String, `var`: String) {
        graph.addVertex(ident)
        graph.addEdge(ident, `var`)
    }

    private fun addFunToMap(ident: String, fn: Function) {
        fns[ident] = fn
    }

    private fun addVarToGraph(name: String) {
        graph.addVertex(name)
    }

    private fun setNeighboursExpired(name: String) {
        if (!graph.containsVertex(name)) {
            return
        }
        val queue = ArrayDeque<String>()
        getAllNeighbours(name).forEach { queue.add(it) }
        while (!queue.isEmpty()) {
            val curr = queue.first
            queue.pop()
            setFunExpired(fns[curr]!!)
            getAllNeighbours(curr).forEach { queue.add(it) }
        }
    }

    private fun getAllNeighbours(name: String) = Stream.concat(
        graph.incomingEdgesOf(name).stream(),
        graph.outgoingEdgesOf(name).stream()
    )
        .map { listOf(graph.getEdgeSource(it), graph.getEdgeTarget(it)) }
        .flatMap(List<String>::stream)
        .filter { fns.containsKey(it) }
        .filter { !fns[it]!!.expired }

    private fun getOutgoingEdges(name: String) = graph.outgoingEdgesOf(name)
        .map { graph.getEdgeTarget(it) }
        .filter { fns.containsKey(it) }
        .toMutableList()

    private fun setFunExpired(fn: Function) {
        fn.expired = true
    }

    private fun setFunNotExpired(fn: Function) {
        fn.expired = false
    }

    private fun isNameCorrect(name: String) = name.isNotEmpty() && name.first().isLetter() && name.all { it.isLetterOrDigit() || it == '_' }

    private fun isReservedName(name: String) = vars.containsKey(name) || fns.containsKey(name)
}
