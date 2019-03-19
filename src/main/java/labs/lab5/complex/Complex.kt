package labs.lab5.complex

import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class Complex(private val real: Double = 0.0, private val image: Double = 0.0) {
    fun re(): Double {
        return real
    }

    fun im(): Double {
        return image
    }

    fun getMagnitude(): Double {
        return sqrt(real.pow(2) + image.pow(2))
    }

    fun getArgument(): Double {
        return atan2(image, real)
    }

    operator fun plus(addend: Complex): Complex {
        return Complex(real + addend.re(), image + addend.im())
    }

    operator fun plus(addend: Double): Complex {
        return Complex(real + addend, image)
    }

    operator fun minus(subtrahend: Complex): Complex {
        return Complex(real - subtrahend.re(), image - subtrahend.im())
    }

    operator fun minus(subtrahend: Double): Complex {
        return Complex(real - subtrahend, image)
    }

    operator fun times(multiplier: Complex): Complex {
        val left = (real * multiplier.re()) - (image * multiplier.im())
        val right = (real * multiplier.im()) + (image * multiplier.re())
        return Complex(left, right)
    }

    operator fun times(factor: Double): Complex {
        return this * Complex(factor)
    }

    operator fun div(divider: Complex): Complex {
        val common = (divider.re().pow(2) + divider.im().pow(2))
        val left = ((real * divider.re()) + (image * divider.im())) / common
        val right = ((image * divider.re()) - (real * divider.im())) / common
        return Complex(left, right)
    }

    operator fun div(divider: Double): Complex {
        return this / Complex(divider)
    }

    operator fun unaryPlus(): Complex {
        return Complex(real, image)
    }

    operator fun unaryMinus(): Complex {
        return Complex(-real, -image)
    }
}
