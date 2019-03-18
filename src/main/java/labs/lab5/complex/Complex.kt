package labs.lab5.complex

import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class Complex(private val real: Double = 0.0, private val image: Double = 0.0) {
    fun getReal(): Double {
        return real
    }

    fun getImage(): Double {
        return image
    }

    fun getMagnitude(): Double {
        return sqrt(real.pow(2) + image.pow(2))
    }

    fun getArgument(): Double {
        return atan2(image, real)
    }
}
