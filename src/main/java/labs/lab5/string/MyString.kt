package labs.lab5.string

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

fun OutputStream.write(string: MyString) {
    val str = string.getStringData().joinToString("")
    write(str.toByteArray())
}

fun InputStream.read(string: MyString) {
    val input = readLine() ?: throw IOException("Cannot read from input!")
    string.set(input)
}

@Suppress("EqualsOrHashCode")
class MyString constructor() {
    companion object {
        const val NULL_CHAR = '\u0000'
    }

    private var length: Int = 0
    private var string: Array<Char> = arrayOf(NULL_CHAR)

    constructor(string: Array<Char>) : this() {
        this.length = string.size
        this.string = string
    }

    constructor(string: Array<Char>, length: Int) : this() {
        if (length < 0 || length > string.size) {
            throw StringIndexOutOfBoundsException("Cannot initialize string with $length length!")
        }
        this.length = length
        this.string = copyCharArray(string, length)
    }

    constructor(other: MyString) : this() {
        this.length = other.length
        this.string = copyCharArray(other.string, other.length)
    }

    constructor(other: String) : this() {
        this.length = other.length
        this.string = stringToCharArray(other)
    }

    fun set(string: String) {
        this.length = string.length
        this.string = stringToCharArray(string)
    }

    fun getLength(): Int {
        return length
    }

    fun getStringData(): Array<Char> {
        return string + NULL_CHAR
    }

    fun substring(start: Int, length: Int): MyString {
        if (start + length > this.length || start + length < start) {
            throw StringIndexOutOfBoundsException("Begin $start, end $${start + length}, length ${this.length}!")
        }
        var array = emptyArray<Char>()
        val end = start + length - 1
        for (i in start..end) {
            array += string[i]
        }
        return MyString(array, length)
    }

    fun clear() {
        length = 0
        string = arrayOf(NULL_CHAR)
    }

    operator fun plus(other: MyString): MyString {
        return MyString(string + other.string)
    }

    operator fun plus(other: String): MyString {
        return MyString(string + stringToCharArray(other))
    }

    operator fun plus(other: Array<Char>): MyString {
        return MyString(string + other)
    }

    operator fun plusAssign(other: MyString) {
        string += other.string
        length += other.length
    }

    override operator fun equals(other: Any?): Boolean {
        return when (other) {
            is MyString -> string.contentEquals(other.string)
            is String -> string.contentEquals(stringToCharArray(other))
            else -> return false
        }
    }

    operator fun set(index: Int, value: Char) {
        string[index] = value
    }

    operator fun get(index: Int): Char {
        return string[index]
    }

    operator fun compareTo(other: MyString): Int {
        return string.joinToString("").compareTo(other.string.joinToString(""))
    }

    private fun stringToCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }

    private fun copyCharArray(chars: Array<Char>, length: Int): Array<Char> {
        return Array(length) { i -> chars[i] }
    }
}
