package labs.lab5.string

import labs.lab5.string.MyString.Companion.NULL_CHAR
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows

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
        assertThat(expected, `is`(actual.getLength()))
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
        assertThat(expected, `is`(actual.getLength()))
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
        assertThat(expected, `is`(actual.getLength()))
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
        assertThat(expected, `is`(actual.getLength()))
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
