package labs.lab3.calculator.io

import labs.lab3.calculator.IOperatable

interface IHandler {
    fun getTokenHandler(token: String): (operatable: IOperatable) -> Unit
}
