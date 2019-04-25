package labs.lab3.calculator.calculator

class FastCalculator : BaseCalculator() {
    override fun setVarValue(name: String, value: String) {
        super.setVarValue(name, value)
        traverseAndCalcFunsValues(name, fns)
    }

    override fun setFun(ident: String, `var`: String) {
        super.setFun(ident, `var`)
        setFunValue(fns[ident]!!)
    }

    override fun setFun(ident: String, left: String, op: Operator, right: String) {
        super.setFun(ident, left, op, right)
        setFunValue(fns[ident]!!)
    }

    private fun setFunValue(fn: Function) = when {
        fn.op == null -> fn.value = getValue(fn.left)
        else -> calcFunValue(fn)
    }

    private fun traverseAndCalcFunsValues(name: String, fns: Map<String, Function>) {
        val calculated = HashSet<String>()
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
}
