package labs.lab3.calculator.operations

import labs.lab3.calculator.Operator

interface ISetFun {
    fun setFun(ident: String, left: String, op: Operator, right: String)

    fun setFun(ident: String, `var`: String)
}
