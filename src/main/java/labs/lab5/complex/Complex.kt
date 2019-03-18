package labs.lab5.complex

import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class Complex(private var real: Double = 0.0, private var image: Double = 0.0) {
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
        real += addend.re()
        image += addend.im()
        return this
    }

    operator fun plus(addend: Double): Complex {
        real += addend
        return this
    }

    operator fun minus(subtrahend: Complex): Complex {
        real -= subtrahend.re()
        image -= subtrahend.im()
        return this
    }

    operator fun minus(subtrahend: Double): Complex {
        real -= subtrahend
        return this
    }
}
