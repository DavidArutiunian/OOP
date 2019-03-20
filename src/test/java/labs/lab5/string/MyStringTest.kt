package labs.lab5.string

import labs.lab5.string.MyString.Companion.NULL_CHAR
import lib.io.OutputMock
import lib.io.OutputMock.setSystemInput
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.lang.System.`in`
import java.lang.System.out

@Suppress("ReplaceCallWithBinaryOperator")
class MyStringTest {
    @Test
    fun `constructor(chars) has correct data`() {
        val chars = getMockArray()
        val actual = MyString(chars)
        val expected = chars + NULL_CHAR
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(chars) has correct length`() {
        val chars = getMockArray()
        val actual = MyString(chars)
        val expected = chars.size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `constructor(chars) with null char in middle has correct length`() {
        val chars = arrayOf('H', 'e', 'l', 'l', 'o', ',', NULL_CHAR, 'W', 'o', 'r', 'l', 'd')
        val actual = MyString(chars)
        val expected = chars.size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `constructor(chars) with null char in middle has correct data`() {
        val chars = arrayOf('H', 'e', 'l', 'l', 'o', ',', NULL_CHAR, 'W', 'o', 'r', 'l', 'd')
        val actual = MyString(chars)
        val expected = chars + NULL_CHAR
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(chars, length) has correct data`() {
        val chars = getMockArray()
        val actual = MyString(chars, chars.size)
        val expected = chars + NULL_CHAR
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(chars, length) has correct length`() {
        val chars = getMockArray()
        val actual = MyString(chars, chars.size)
        val expected = chars.size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `constructor(chars, length) has correct data if length is less then chars`() {
        val chars = getMockArray()
        val actual = MyString(chars, chars.size - 7)
        val expected = arrayOf('H', 'e', 'l', 'l', 'o', NULL_CHAR)
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(chars, length) has correct length if length is less then chars`() {
        val chars = getMockArray()
        val actual = MyString(chars, chars.size - 7)
        val expected = arrayOf('H', 'e', 'l', 'l', 'o').size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `constructor(chars, length) has correct data if length is 0`() {
        val chars = getMockArray()
        val actual = MyString(chars, 0)
        val expected = arrayOf(NULL_CHAR)
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(chars, length) has correct length if length is 0`() {
        val chars = getMockArray()
        val actual = MyString(chars, 0)
        val expected = emptyArray<Char>().size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `constructor(chars, length) throws if length is negative`() {
        val chars = getMockArray()
        assertThrows<StringIndexOutOfBoundsException> { MyString(chars, -chars.size) }
    }

    @Test
    fun `constructor(chars, length) throws if length is more then string has chars`() {
        val chars = getMockArray()
        assertThrows<StringIndexOutOfBoundsException> { MyString(chars, 2 * chars.size) }
    }

    @Test
    fun `constructor(MyString) has correct data`() {
        val chars = getMockArray()
        val string = MyString(chars)
        val actual = MyString(string)
        val expected = chars + NULL_CHAR
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(MyString) has correct length`() {
        val chars = getMockArray()
        val string = MyString(chars)
        val actual = MyString(string)
        val expected = chars.size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `constructor(string) has correct data`() {
        val actual = MyString(getMockString())
        val expected = getMockString() + NULL_CHAR
        assertThat(toCharArray(expected), `is`(actual.getStringData()))
    }

    @Test
    fun `constructor(string) has correct length`() {
        val actual = MyString(getMockString())
        val expected = getMockString().length
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `substring has correct data`() {
        val string = MyString(getMockString())
        val actual = string.substring(7, 5)
        val chars = getMockString().substring(7, 8 + 4)
        val expected = MyString(chars)
        assertThat(expected.getStringData(), `is`(actual.getStringData()))
    }

    @Test
    fun `substring has correct length`() {
        val string = MyString(getMockString())
        val actual = string.substring(7, 5)
        val chars = getMockString().substring(7, 8 + 4)
        val expected = MyString(chars)
        assertThat(expected.getLength(), `is`(actual.getLength()))
    }

    @Test
    fun `substring has correct data with zero length`() {
        val string = MyString(getMockString())
        val actual = string.substring(7, 0)
        val chars = emptyArray<Char>()
        val expected = MyString(chars)
        assertThat(expected.getStringData(), `is`(actual.getStringData()))
    }

    @Test
    fun `substring has correct length with zero length`() {
        val string = MyString(getMockString())
        val actual = string.substring(7, 0)
        val chars = emptyArray<Char>()
        val expected = MyString(chars)
        assertThat(expected.getLength(), `is`(actual.getLength()))
    }

    @Test
    fun `substring throws error if out of range`() {
        val actual = MyString(getMockString())
        assertThrows<StringIndexOutOfBoundsException> { actual.substring(7, 6) }
    }

    @Test
    fun `substring throws error if length is negative`() {
        val actual = MyString(getMockString())
        assertThrows<StringIndexOutOfBoundsException> { actual.substring(7, -5) }
    }

    @Test
    fun `clear data`() {
        val actual = MyString(getMockString())
        actual.clear()
        val expected = MyString()
        assertThat(expected.getStringData(), `is`(actual.getStringData()))
    }

    @Test
    fun `clear length`() {
        val actual = MyString(getMockString())
        actual.clear()
        val expected = MyString()
        assertThat(expected.getLength(), `is`(actual.getLength()))
    }

    @Test
    fun `plus has correct data with MyString`() {
        val string = MyString(getMockString())
        val other = MyString(getMockString())
        val actual = string + other
        val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `plus has correct length with MyString`() {
        val string = MyString(getMockString())
        val other = MyString(getMockString())
        val actual = string + other
        val expected = toCharArray(getMockString() + getMockString()).size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `plus has correct data with String`() {
        val string = MyString(getMockString())
        val other = getMockString()
        val actual = string + other
        val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `plus has correct length with String`() {
        val string = MyString(getMockString())
        val other = getMockString()
        val actual = string + other
        val expected = toCharArray(getMockString() + getMockString()).size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `plus has correct data with Chars`() {
        val string = MyString(getMockString())
        val other = getMockArray()
        val actual = string + other
        val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `plus has correct length with Chars`() {
        val string = MyString(getMockString())
        val other = getMockArray()
        val actual = string + other
        val expected = toCharArray(getMockString() + getMockString()).size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `plus assign has correct data`() {
        val actual = MyString(getMockString())
        val other = MyString(getMockString())
        actual += other
        val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
        assertThat(expected, `is`(actual.getStringData()))
    }

    @Test
    fun `plus assign has correct length`() {
        val actual = MyString(getMockString())
        val other = MyString(getMockString())
        actual += other
        val expected = toCharArray(getMockString() + getMockString()).size
        assertEquals(actual.getLength(), expected)
    }

    @Test
    fun `equal with MyString`() {
        val string = MyString(getMockString())
        val other = MyString(getMockString())
        assertTrue(string == other)
        assertFalse(string != other)
    }

    @Test
    fun `not equal with MyString`() {
        val string = MyString(getMockString())
        val other = MyString(getMockString() + getMockString())
        assertTrue(string != other)
        assertFalse(string == other)
    }

    @Test
    fun `equal with String`() {
        val string = MyString(getMockString())
        val other = getMockString()
        assertTrue(string.equals(other))
        assertFalse(!string.equals(other))
    }

    @Test
    fun `not equal with String`() {
        val string = MyString(getMockString())
        val other = getMockString() + getMockString()
        assertTrue(!string.equals(other))
        assertFalse(string.equals(other))
    }

    @Test
    fun `not equal with any other type`() {
        val string = MyString(getMockString())
        assertFalse(string.equals(10))
        assertFalse(string.equals(null))
        assertFalse(string.equals(true))
    }

    @Test
    fun `less more with MyString`() {
        val string = MyString("abc")
        val other = MyString("bbc")
        assertTrue(string < other)
        assertTrue(other > string)
        assertFalse(string > other)
        assertFalse(other < string)
    }

    @Test
    fun `less equal more equal with MyString`() {
        val string = MyString("abc")
        val other = MyString("abc")
        assertTrue(string <= other)
        assertTrue(other >= string)
        assertFalse(string < other)
        assertFalse(string > other)
        assertFalse(other > string)
        assertFalse(other < string)
    }

    @Test
    fun set() {
        val actual = MyString(getMockString())
        val expected = toCharArray("Hello! World") + NULL_CHAR
        actual[5] = '!'
        assertThat(actual.getStringData(), `is`(expected))
    }

    @Test
    fun `set throws if out of bounds`() {
        val string = MyString(getMockString())
        assertThrows<ArrayIndexOutOfBoundsException> { string[12] = '!' }
    }

    @Test
    fun get() {
        val string = MyString(getMockString())
        val expected = 'H'
        val actual = string[0]
        assertEquals(expected, actual)
    }

    @Test
    fun `get throws out of bounds`() {
        val string = MyString(getMockString())
        assertThrows<ArrayIndexOutOfBoundsException> { string[12] }
    }

    @Test
    fun print() {
        val mock = OutputMock()
        val string = MyString(getMockString())
        out.write(string)
        val expected = getMockString() + NULL_CHAR
        assertEquals(expected, mock.read())
        mock.destruct()
    }

    @Test
    fun read() {
        setSystemInput(getMockString())
        val actual = MyString()
        `in`.read(actual)
        val expected = toCharArray(getMockString()) + NULL_CHAR
        assertThat(actual.getStringData(), `is`(expected))
    }

    private fun getMockArray(): Array<Char> {
        return arrayOf('H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd')
    }

    private fun getMockString(): String {
        return "Hello, World"
    }

    private fun toCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }
}
