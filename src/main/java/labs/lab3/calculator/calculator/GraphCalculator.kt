package labs.lab3.calculator.calculator

import labs.lab3.calculator.graphs.GraphFactory

class GraphCalculator : BaseCalculator() {
    private val graph = GraphFactory.createCustomGraph<String>()

    override fun setVar(name: String) {
        super.setVar(name)
        addVarToGraph(name)
    }

    override fun setVarValue(name: String, value: String) {
        super.setVarValue(name, value)
        addVarToGraph(name)
        setNeighboursExpired(name)
    }

    override fun setFun(ident: String, `var`: String) {
        super.setFun(ident, `var`)
        addFunToGraph(ident, `var`)
    }

    override fun setFun(ident: String, left: String, op: Operator, right: String) {
        super.setFun(ident, left, op, right)
        addFunToGraph(ident, fns[ident]!!)
    }

    override fun getFns(): Map<String, Function> {
        for (`var` in vars) {
            calcFunsValues(`var`.key)
        }
        return super.getFns()
    }

    override fun getValue(name: String): Double {
        if (fns.containsKey(name)) {
            calcFunsValues(name)
        }
        return super.getValue(name)
    }

    private fun calcFunsValues(name: String) {
        if (!fns.containsKey(name)) {
            return
        }
        val nodes = graph
            .getAllNeighbours(name)
            .filter { fns.containsKey(it) }
            .asReversed()
        for (fn in nodes) {
            setFunValue(fns[fn]!!)
            setFunNotExpired(fns[fn]!!)
        }
    }

    private fun setFunValue(fn: Function) = when {
        fn.op == null -> fn.value = super.getValue(fn.left)
        else -> calcFunValue(fn)
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

    private fun addVarToGraph(name: String) {
        graph.addVertex(name)
    }

    private fun setNeighboursExpired(name: String) {
        if (!graph.containsVertex(name)) {
            return
        }
        graph.getAllNeighbours(name)
            .filter { fns.containsKey(it) }
            .forEach { setFunExpired(fns[it]!!) }
    }

    private fun setFunExpired(fn: Function) {
        fn.expired = true
    }

    private fun setFunNotExpired(fn: Function) {
        fn.expired = false
    }
}
