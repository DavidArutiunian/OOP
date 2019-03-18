package labs.lab5.complex

import org.junit.Assert.assertEquals
import org.junit.Test

class ComplexTest {
    companion object {
        const val EPS = 10e-5
    }

    @Test
    fun getReal() {
        val complex = Complex(2.0, 1.0)
        val expected = 2.0
        assertEquals(expected, complex.getReal(), EPS)
    }

    @Test
    fun getImage() {
        val complex = Complex(2.0, 1.0)
        val expected = 1.0
        assertEquals(expected, complex.getImage(), EPS)
    }

    @Test
    fun getMagnitude() {
        val complex = Complex(2.0, 1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getMagnitudeNegativeReal() {
        val complex = Complex(-2.0, 1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getMagnitudeNegativeImage() {
        val complex = Complex(2.0, -1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getMagnitudeNegativeBoth() {
        val complex = Complex(-2.0, -1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getMagnitudeZeroReal() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.0
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getMagnitudeZeroImage() {
        val complex = Complex(2.0, -1.0)
        val expected = 2.23606
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getMagnitudeZeroBoth() {
        val complex = Complex(0.0, 0.0)
        val expected = 0.0
        assertEquals(expected, complex.getMagnitude(), EPS)
    }

    @Test
    fun getArgument() {
        val complex = Complex(2.0, 1.0)
        val expected = 0.46364
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun getArgumentNegativeReal() {
        val complex = Complex(-2.0, 1.0)
        val expected = 2.67794
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun getArgumentNegativeImage() {
        val complex = Complex(2.0, -1.0)
        val expected = -0.46364
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun getArgumentNegativeBoth() {
        val complex = Complex(-2.0, -1.0)
        val expected = -2.67794
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun getArgumentZeroReal() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.57079
        assertEquals(expected, complex.getArgument(), EPS)
    }

    @Test
    fun getArgumentZeroImage() {
        val complex = Complex(2.0, 0.0)
        val expected = 0.0
        assertEquals(expected, complex.getArgument(), EPS)
    }
}
