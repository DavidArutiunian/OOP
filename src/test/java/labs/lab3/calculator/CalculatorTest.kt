package labs.lab3.calculator

import labs.lab3.calculator.exceptions.IncorrectVarNameException
import labs.lab3.calculator.exceptions.ReservedVarNameException
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows

class CalculatorTest {
    @TestFactory
    fun `set var test factory`() = listOf("foo", "bar", "baz23", "foo45bar23baz")
        .map { name ->
            dynamicTest("test set var works for '$name'") {
                val calc = Calculator()
                calc.setVar(name)
                assertEquals(Double.NaN, calc.vars[name])
            }
        }

    @TestFactory
    fun `set var throws IncorrectVarNameException factory`() = listOf("1foo", "foo_bar", "\$baz", "@const")
        .map { name ->
            dynamicTest("test set var throws IncorrectVarNameException for $name") {
                val calc = Calculator()
                assertThrows<IncorrectVarNameException> { calc.setVar(name) }
            }
        }

    @TestFactory
    fun `set far throws ReservedVarNameException factory`() = listOf("foo", "bar", "baz")
        .map { name ->
            dynamicTest("test set var throws ReservedVarNameException for $name") {
                val calc = Calculator()
                calc.setVar(name)
                assertThrows<ReservedVarNameException> { calc.setVar(name) }
            }
        }
}
