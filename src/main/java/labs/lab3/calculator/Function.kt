package labs.lab3.calculator

data class Function(val left: String, val right: String, val op: Operator, var value: Double = Double.NaN)
