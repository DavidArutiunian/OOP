package labs.lab5.complex

import org.junit.Assert.assertEquals
import org.junit.Test

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
}
