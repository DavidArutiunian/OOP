package labs.lab3.calculator

import labs.lab3.calculator.io.CommandLineHandler
import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val calc = Calculator()
    val handler = CommandLineHandler(sc)
    var token: String
    while (true) {
        try {
            token = sc.next()
            if (token == "exit") {
                break
            }
            val method = handler.getTokenHandler(token)
            method.invoke(calc)
        } catch (ex: Exception) {
            print(ex.message)
        }
    }
}
