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
    fun `set var test`() = listOf("foo", "bar", "baz23", "foo45bar23baz", "foo_bar_baz")
        .map { name ->
            dynamicTest("test set var works for $name") {
                val calc = Calculator()
                calc.setVar(name)
                assertEquals(Double.NaN, calc.getValue(name), EPS)
            }
        }

    @TestFactory
    fun `set var throws SyntaxException when var is incorrect`() = listOf("1foo", "\$baz", "@const")
        .map { name ->
            dynamicTest("test set var throws SyntaxException for $name") {
                val calc = Calculator()
                assertThrows<SyntaxException> { calc.setVar(name) }
            }
        }

    @TestFactory
    fun `set var throws SyntaxException when var already defined`() = listOf("foo", "bar", "baz")
        .map { name ->
            dynamicTest("test set var throws SyntaxException for $name") {
                val calc = Calculator()
                calc.setVar(name)
                assertThrows<SyntaxException> { calc.setVar(name) }
            }
        }

    @TestFactory
    fun `set fun test`() = listOf("fn1" to "var1", "foo45bar23baz" to "var2", "baz23" to "bar3")
        .map { pair ->
            dynamicTest("test set fun works for ${pair.first}") {
                val calc = Calculator()
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
            dynamicTest("test set fun throws SyntaxException for ${pair.first}") {
                val calc = Calculator()
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

    @TestFactory
    fun `set var value test`() = with(Calculator()) {
        listOf("foo" to "12.3456", "bar" to "foo", "baz" to "bar")
            .map { pair ->
                dynamicTest("test set var value works for ${pair.first}") {
                    setVarValue(pair.first, pair.second)
                    assertEquals(12.3456, getValue(pair.first), EPS)
                }
            }
    }

    @Test
    fun `set fun sets value if var is defined`() {
        val calc = Calculator()
        calc.setVarValue("foo", "12.3456")
        calc.setFun("fn", "foo")
        assertEquals(12.3456, calc.getValue("fn"), EPS)
        val expected = Function("foo", null, null, 12.3456, false)
        assertThat(calc.getFns()["fn"], `is`(expected))
    }

    @Test
    fun `set fun with left, right and operator`() {
        val calc = Calculator()
        calc.setVarValue("bar", "5.0")
        calc.setVarValue("baz", "5.0")
        calc.setFun("foo", "bar", Operator.ADD, "baz")
        val expected = Function("bar", "baz", Operator.ADD, 10.0, false)
        calc.getValue("foo")
        assertThat(calc.getFns()["foo"], `is`(expected))
        assertEquals(10.0, calc.getValue("foo"), EPS)
    }

    @Test
    fun `scenario with vars`() {
        val calc = Calculator()
        calc.setVar("x")
        assertEquals(Double.NaN, calc.getValue("x"), EPS)
        calc.setVarValue("x", "42")
        assertEquals(42.00, calc.getValue("x"), EPS)
        assertEquals(1, calc.getVars().size)
        calc.setVarValue("x", "1.234")
        assertEquals(1.234, calc.getValue("x"), EPS)
        assertEquals(1, calc.getVars().size)
        calc.setVarValue("y", "x")
        calc.setVarValue("x", "99")
        assertEquals(99.0, calc.getValue("x"), EPS)
        assertEquals(1.234, calc.getValue("y"), EPS)
        assertEquals(2, calc.getVars().size)
    }

    @Test
    fun `scenario with funs`() {
        val calc = Calculator()
        calc.setVar("x")
        calc.setVar("y")
        calc.setFun("XPlusY", "x", Operator.ADD, "y")
        assertEquals(Double.NaN, calc.getValue("XPlusY"), EPS)
        assertEquals(1, calc.getFns().size)
        calc.setVarValue("x", "3")
        calc.setVarValue("y", "4")
        assertEquals(7.0, calc.getValue("XPlusY"), EPS)
        assertEquals(1, calc.getFns().size)
        calc.setVarValue("x", "10")
        assertEquals(14.0, calc.getValue("XPlusY"), EPS)
        calc.setVarValue("z", "3.5")
        calc.setFun("XPlusYDivZ", "XPlusY", Operator.DIV, "z")
        assertEquals(4.0, calc.getValue("XPlusYDivZ"), EPS)
        assertEquals(2, calc.getFns().size)
    }

    @Test
    fun `calc circle area`() {
        val calc = Calculator()
        calc.setVar("radius")
        calc.setVarValue("pi", "3.14159265")
        calc.setFun("radiusSquared", "radius", Operator.MUL, "radius")
        calc.setFun("circleArea", "pi", Operator.MUL, "radiusSquared")
        calc.setVarValue("radius", "10")
        assertEquals(314.159265, calc.getValue("circleArea"), EPS)
        calc.setVarValue("circle10Area", "circleArea")
        calc.setVarValue("radius", "20")
        calc.setVarValue("circle20Area", "circleArea")
        assertEquals(1256.63706, calc.getValue("circleArea"), EPS)
        assertEquals(400.0, calc.getValue("radiusSquared"), EPS)
        assertEquals(2, calc.getFns().size)
        assertEquals(314.159265, calc.getValue("circle10Area"), EPS)
        assertEquals(1256.63706, calc.getValue("circle20Area"), EPS)
        assertEquals(3.14159265, calc.getValue("pi"), EPS)
        assertEquals(20.0, calc.getValue("radius"), EPS)
        assertEquals(4, calc.getVars().size)
    }

    @Test
    fun `calc Fibonacci numbers`() {
        val calc = Calculator()
        calc.setVarValue("v0", "0")
        calc.setVarValue("v1", "1")
        calc.setFun("fib0", "v0")
        calc.setFun("fib1", "v1")
        calc.setFun("fib2", "fib1", Operator.ADD, "fib0")
        calc.setFun("fib3", "fib2", Operator.ADD, "fib1")
        calc.setFun("fib4", "fib3", Operator.ADD, "fib2")
        calc.setFun("fib5", "fib4", Operator.ADD, "fib3")
        calc.setFun("fib6", "fib5", Operator.ADD, "fib4")
        assertEquals(0.0, calc.getValue("fib0"), EPS)
        assertEquals(1.0, calc.getValue("fib1"), EPS)
        assertEquals(1.0, calc.getValue("fib2"), EPS)
        assertEquals(2.0, calc.getValue("fib3"), EPS)
        assertEquals(3.0, calc.getValue("fib4"), EPS)
        assertEquals(5.0, calc.getValue("fib5"), EPS)
        assertEquals(8.0, calc.getValue("fib6"), EPS)
        assertEquals(7, calc.getFns().size)
        calc.setVarValue("v0", "1")
        calc.setVarValue("v1", "1")
        assertEquals(1.0, calc.getValue("fib0"), EPS)
        assertEquals(1.0, calc.getValue("fib1"), EPS)
        assertEquals(2.0, calc.getValue("fib2"), EPS)
        assertEquals(3.0, calc.getValue("fib3"), EPS)
        assertEquals(5.0, calc.getValue("fib4"), EPS)
        assertEquals(8.0, calc.getValue("fib5"), EPS)
        assertEquals(13.0, calc.getValue("fib6"), EPS)
        assertEquals(7, calc.getFns().size)
    }

    @Test
    fun `calc 1 000 000 funs recursively`() {
        val calc = Calculator()
        calc.setVarValue("x1", "1")
        for (i in 2..1_000_000) {
            calc.setFun("x$i", "x${i - 1}", Operator.ADD, "x1")
        }
        assertEquals(1_000_000.0, calc.getValue("x1000000"), EPS)
        assertEquals(999_999, calc.getFns().size)
        calc.setVarValue("x1", "2")
        assertEquals(2_000_000.0, calc.getValue("x1000000"), EPS)
        assertEquals(999_999, calc.getFns().size)
    }
}
