package labs.lab3.calculator.operations

import labs.lab3.calculator.Function


interface IFunctionsGetter {
    fun getFns(): Map<String, Function>
}
