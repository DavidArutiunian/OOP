package labs.lab5.complex

import lib.io.OutputMock
import lib.io.OutputMock.setSystemInput
import org.junit.Assert.*
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import java.lang.System.`in`
import java.lang.System.out

@Suppress("ReplaceCallWithBinaryOperator")
class ComplexTest {
    companion object {
        const val EPS = 10e-5
    }

    @Test
    fun `get real part`() {
        val complex = Complex(2.0, 1.0)
        val expected = 2.0
        assertEquals(expected, complex.re(), EPS)
    }

    @Test
    fun `get imaginary part`() {
        val complex = Complex(2.0, 1.0)
        val expected = 1.0
        assertEquals(expected, complex.im(), EPS)
    }

    @Test
    fun `get magnitude`() {
        val complex = Complex(2.0, 1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get magnitude if real negative`() {
        val complex = Complex(-2.0, 1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get magnitude if imaginary negative`() {
        val complex = Complex(2.0, -1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get magnitude if both parts negative`() {
        val complex = Complex(-2.0, -1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get magnitude if real is zero`() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.0
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get magnitude if imaginary is zero`() {
        val complex = Complex(2.0, 0.0)
        val expected = 2.0
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get magnitude if both zero`() {
        val complex = Complex(0.0, 0.0)
        val expected = 0.0
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun `get argument`() {
        val complex = Complex(2.0, 1.0)
        val expected = 0.46364
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `get argument if real is negative`() {
        val complex = Complex(-2.0, 1.0)
        val expected = 2.67794
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `get argument if imaginary is negative`() {
        val complex = Complex(2.0, -1.0)
        val expected = -0.46364
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `get argument if both parts are negative`() {
        val complex = Complex(-2.0, -1.0)
        val expected = -2.67794
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `get argument if real is zero`() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.57079
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `get argument if imaginary is zero`() {
        val complex = Complex(2.0, 0.0)
        val expected = 0.0
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `get argument if both parts are zero`() {
        val complex = Complex(0.0, 0.0)
        val expected = 0.0
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun `add complex to complex`() {
        val complex = Complex(2.0, 1.0)
        val addend = Complex(1.0, 2.0)
        val expected = Complex(3.0, 3.0)
        val actual = complex + addend
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `add double to complex`() {
        val complex = Complex(2.0, 1.0)
        val addend = 1.0
        val expected = Complex(3.0, 1.0)
        val actual = complex + addend
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `subtract complex from complex`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = Complex(3.0, 2.0)
        val expected = Complex(-1.0, -1.0)
        val actual = complex - subtrahend
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `subtract double from complex`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = 1.0
        val expected = Complex(1.0, 1.0)
        val actual = complex - subtrahend
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `multiply complex by complex`() {
        val complex = Complex(2.0, 1.0)
        val multiplier = Complex(4.0, 5.0)
        val expected = Complex(3.0, 14.0)
        val actual = complex * multiplier
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `multiply complex by double`() {
        val complex = Complex(2.0, 1.0)
        val factor = 2.0
        val expected = Complex(4.0, 2.0)
        val actual = complex * factor
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `divide complex by complex`() {
        val complex = Complex(2.0, 1.0)
        val divider = Complex(4.0, 5.0)
        val expected = Complex(0.31707, -0.14634)
        val actual = complex / divider
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `divide complex by double`() {
        val complex = Complex(2.0, 1.0)
        val divider = 2.0
        val expected = Complex(1.0, 0.5)
        val actual = complex / divider
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    @Test
    fun `unary plus returns copy`() {
        val complex = Complex(2.0, 1.0)
        val copy = +complex
        assertNotEquals(copy.toString(), complex.toString())
    }

    @Test
    fun `unary minus returns inverted copy`() {
        val complex = Complex(2.0, 1.0)
        val inverted = -complex
        val expected = Complex(-2.0, -1.0)
        assertEquals(expected.re(), inverted.re(), EPS)
        assertEquals(expected.im(), inverted.im(), EPS)
    }

    @Test
    fun `plus assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val addend = Complex(1.0, 1.0)
        val expected = Complex(3.0, 2.0)
        complex += addend
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `plus assign by double`() {
        val complex = Complex(2.0, 1.0)
        val addend = 2.0
        val expected = Complex(4.0, 1.0)
        complex += addend
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `minus assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = Complex(1.0, 1.0)
        val expected = Complex(1.0, 0.0)
        complex -= subtrahend
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `minus assign by double`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = 2.0
        val expected = Complex(0.0, 1.0)
        complex -= subtrahend
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `times assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val multiplier = Complex(1.0, 1.0)
        val expected = Complex(1.0, 3.0)
        complex *= multiplier
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `times assign by double`() {
        val complex = Complex(2.0, 1.0)
        val factor = 2.0
        val expected = Complex(4.0, 2.0)
        complex *= factor
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `div assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val divider = Complex(1.0, 1.0)
        val expected = Complex(1.5, -0.5)
        complex /= divider
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `div assign by double`() {
        val complex = Complex(2.0, 1.0)
        val divider = 2.0
        val expected = Complex(1.0, 0.5)
        complex /= divider
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `equals to other complex`() {
        val complex = Complex(2.0, 1.0)
        val other = Complex(2.0, 1.0)
        assertTrue(complex == other)
        assertFalse(complex != other)
    }


    @Test
    fun `not equals to other complex`() {
        val complex = Complex(2.0, 1.0)
        val other = Complex(1.0, 1.0)
        assertFalse(complex == other)
        assertTrue(complex != other)
    }

    @Test
    fun `equals to double`() {
        val complex = Complex(2.0, 0.0)
        val other = 2.0
        assertTrue(complex.equals(other))
        assertFalse(!complex.equals(other))
    }

    @Test
    fun `not equals to double`() {
        val complex = Complex(2.0, 0.0)
        val other = 1.0
        assertFalse(complex.equals(other))
        assertTrue(!complex.equals(other))
    }

    @Test
    fun `print positive`() {
        val mock = OutputMock()
        val complex = Complex(2.0, 1.0)
        out.writeln(complex)
        val expected = "2.0+1.0i"
        assertEquals(expected, mock.read())
        mock.destruct()
    }

    @Test
    fun `print negative`() {
        val mock = OutputMock()
        val complex = Complex(-2.0, -1.0)
        out.writeln(complex)
        val expected = "-2.0-1.0i"
        assertEquals(expected, mock.read())
        mock.destruct()
    }

    @Test
    fun `read positive`() {
        setSystemInput("4+2i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(4.0, 2.0)
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `read positive double`() {
        setSystemInput("4.2+2.8i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(4.2, 2.8)
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `read negative`() {
        setSystemInput("-4-2i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(-4.0, -2.0)
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `read negative double`() {
        setSystemInput("-4.2-2.8i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(-4.2, -2.8)
        assertEquals(expected.re(), complex.re(), EPS)
        assertEquals(expected.im(), complex.im(), EPS)
    }

    @Test
    fun `corrupted real part`() {
        setSystemInput("a+2i")
        val complex = Complex()
        assertThrows<IOException> { `in`.read(complex) }
    }

    @Test
    fun `corrupted imaginary part`() {
        setSystemInput("4+ai")
        val complex = Complex()
        assertThrows<IOException> { `in`.read(complex) }
    }
}


