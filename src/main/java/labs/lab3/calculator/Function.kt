package labs.lab3.calculator

data class Function(var left: String, var right: String? = null, var op: Operator? = null, var value: Double = Double.NaN)
