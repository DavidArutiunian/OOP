package labs.lab3.calculator

import labs.lab3.calculator.exceptions.IncorrectVarNameException
import labs.lab3.calculator.exceptions.ReservedVarNameException

class Calculator {
    var vars = HashMap<String, Double>()
        private set
    var fns = HashMap<String, Function>()
        private set

    fun setVar(name: String) {
        when {
            !isNameCorrect(name) -> throw IncorrectVarNameException("Incorrect var name!")
            isReservedName(name) -> throw ReservedVarNameException("Var name is reserved!")
            else -> vars[name] = Double.NaN
        }
    }

    private fun isNameCorrect(name: String): Boolean {
        return !(name.isEmpty() || !name.first().isLetter() || !name.all { it.isLetterOrDigit() })
    }

    private fun isReservedName(name: String): Boolean {
        return vars.containsKey(name) || fns.containsKey(name)
    }
}
