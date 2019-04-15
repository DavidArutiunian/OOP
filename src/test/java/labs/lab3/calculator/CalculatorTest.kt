package labs.lab3.calculator

import labs.lab3.calculator.exceptions.ReferenceException
import labs.lab3.calculator.exceptions.SyntaxException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows

class CalculatorTest {
    companion object {
        const val EPS = 10e-15
    }

    @TestFactory
    fun `set var test`() = listOf("foo", "bar", "baz23", "foo45bar23baz")
        .map { name ->
            val calc = Calculator()
            dynamicTest("test set var works for $name") {
                calc.setVar(name)
                assertEquals(Double.NaN, calc.getValue(name), EPS)
            }
        }

    @TestFactory
    fun `set var throws SyntaxException when var is incorrect`() = listOf("1foo", "foo_bar", "\$baz", "@const")
        .map { name ->
            val calc = Calculator()
            dynamicTest("test set var throws SyntaxException for $name") {
                assertThrows<SyntaxException> { calc.setVar(name) }
            }
        }

    @TestFactory
    fun `set var throws SyntaxException when var already defined`() = listOf("foo", "bar", "baz")
        .map { name ->
            val calc = Calculator()
            dynamicTest("test set var throws SyntaxException for $name") {
                calc.setVar(name)
                assertThrows<SyntaxException> { calc.setVar(name) }
            }
        }

    @TestFactory
    fun `set fun test factory`() = listOf("fn1" to "var1", "foo45bar23baz" to "var2", "baz23" to "bar3")
        .map { pair ->
            val calc = Calculator()
            dynamicTest("test set fun works for ${pair.first}") {
                calc.setVar(pair.second)
                calc.setFun(pair.first, pair.second)
                assertEquals(Double.NaN, calc.getValue(pair.first), EPS)
                assertTrue(calc.getFns().containsKey(pair.first))
                assertTrue(calc.getVars().containsKey(pair.second))
            }
        }

    @Test
    fun `set fun throws SyntaxException when name is reserved`() {
        val calc = Calculator()
        calc.setVar("foo")
        calc.setVar("baz")
        calc.setFun("bar", "foo")
        assertThrows<SyntaxException> { calc.setFun("bar", "baz") }
    }

    @TestFactory
    fun `set fun throws SyntaxException when name is incorrect`() = listOf("1fn1" to "var1", "#fn2" to "var2", "\$fn3" to "bar3")
        .map { pair ->
            val calc = Calculator()
            dynamicTest("test set fun throws SyntaxException for ${pair.first}") {
                calc.setVar(pair.second)
                assertThrows<SyntaxException> { calc.setFun(pair.first, pair.second) }
            }
        }

    @Test
    fun `set fun throws ReferenceException when var is not defined`() {
        val calc = Calculator()
        assertThrows<ReferenceException> { calc.setFun("foo", "bar") }
    }

    @Test
    fun `get vars return map with vars`() {
        val calc = Calculator()
        calc.setVar("foo")
        calc.setVar("bar")
        calc.setVar("baz")
        val actual = calc.getVars()
        val expected = mapOf("foo" to Double.NaN, "bar" to Double.NaN, "baz" to Double.NaN)
        assertThat(actual, `is`(expected))
    }
}
