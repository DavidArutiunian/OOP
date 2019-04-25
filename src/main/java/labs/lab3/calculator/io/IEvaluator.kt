package labs.lab3.calculator.io

import labs.lab3.calculator.calculator.Function
import labs.lab3.calculator.calculator.Operator

interface IEvaluator {
    fun setVar(name: String)

    fun getVars(): Map<String, Double>

    fun setVarValue(name: String, value: String)

    fun setFun(ident: String, left: String, op: Operator, right: String)

    fun setFun(ident: String, `var`: String)

    fun getFns(): Map<String, Function>

    fun getValue(name: String): Double
}
