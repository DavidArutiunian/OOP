package labs.lab3.calculator

import labs.lab3.calculator.calculator.GraphCalculator
import labs.lab3.calculator.io.CommandLineHandler
import labs.lab3.calculator.io.ECommandToken
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val calc = GraphCalculator()
    val handler = CommandLineHandler(calc, sc)
    var token: String
    while (true) {
        try {
            token = sc.next()
            if (token == ECommandToken.EXIT.toString()) {
                break
            }
            handler.eval(token)
        } catch (ex: Exception) {
            print(ex.message)
        }
    }
}
