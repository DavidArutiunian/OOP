package labs.lab7.find_max_ex

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class FindMaxKtTest {
    @Test
    fun `find max of integers max at end`() {
        val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val max = findMax(array)
        assertEquals(10, max)
    }

    @Test
    fun `find max of integers max at middle`() {
        val array = arrayOf(0, 1, 2, 3, 4, 10, 5, 6, 7, 8, 9)
        val max = findMax(array)
        assertEquals(10, max)
    }

    @Test
    fun `find max of integers max at start`() {
        val array = arrayOf(10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val max = findMax(array)
        assertEquals(10, max)
    }

    @Test
    fun `return null if no max value found`() {
        val array = emptyArray<Int>()
        val max = findMax(array)
        assertNull(max)
    }

    @Test
    fun `find max of double max at end`() {
        val array = arrayOf(0.000001, 0.000002, 0.000003, 0.000004, 0.000005)
        val max = findMax(array)
        assertEquals(0.000005, max)
    }

    @Test
    fun `find max of double max at middle`() {
        val array = arrayOf(0.000001, 0.000002, 0.000005, 0.000003, 0.000004)
        val max = findMax(array)
        assertEquals(0.000005, max)
    }

    @Test
    fun `find max of double max at start`() {
        val array = arrayOf(0.000005, 0.000001, 0.000002, 0.000003, 0.000004)
        val max = findMax(array)
        assertEquals(0.000005, max)
    }

    @Test
    fun `find max of strings max at end`() {
        val array = arrayOf("abcdefg", "abcdeff", "abcdeee", "abcdddd", "abccccc", "abbbbbb", "aaaaaaa")
        val max = findMax(array)
        assertEquals("aaaaaaa", max)
    }

    @Test
    fun `find max of strings max at middle`() {
        val array = arrayOf("abcdefg", "abcdeff", "abcdeee", "aaaaaaa", "abcdddd", "abccccc", "abbbbbb")
        val max = findMax(array)
        assertEquals("aaaaaaa", max)
    }

    @Test
    fun `find max of strings max at start`() {
        val array = arrayOf("aaaaaaa", "abcdefg", "abcdeff", "abcdeee", "abcdddd", "abccccc", "abbbbbb")
        val max = findMax(array)
        assertEquals("aaaaaaa", max)
    }

    @Test
    fun `fin max of humans bu growth`() {
        data class Human(val name: String, val growth: Double, val weight: Double)

        val expected = Human("Cecil Shaeffer", 200.0, 198.0)
        val array = arrayOf(
            Human("Obdulia Belisle", 175.0, 65.0),
            expected,
            Human("Graig Herriman", 168.0, 50.0)
        )
        val max = findMax(array, { max, current -> max.growth < current.growth })
        assertThat(max, `is`(expected))
    }

    @Test
    fun `fin max of humans bu weight`() {
        data class Human(val name: String, val growth: Double, val weight: Double)

        val expected = Human("Cecil Shaeffer", 200.0, 198.0)
        val array = arrayOf(
            expected,
            Human("Obdulia Belisle", 175.0, 65.0),
            Human("Graig Herriman", 168.0, 50.0)
        )
        val max = findMax(array, { max, current -> max.weight < current.weight })
        assertThat(max, `is`(expected))
    }
}
