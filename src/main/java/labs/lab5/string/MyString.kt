package labs.lab5.string

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
        this.string = Array(length) { i -> string[i] }
    }

    constructor(other: MyString) : this() {
        this.length = other.length
        this.string = other.string
    }

    constructor(other: String) : this() {
        this.length = other.length
        this.string = Array(this.length) { i -> other[i] }
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
        return MyString(string + toCharArray(other))
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
            is String -> string.contentEquals(toCharArray(other))
            else -> return false
        }
    }

    operator fun compareTo(other: MyString): Int {
        return string.contentToString().compareTo(other.string.contentToString())
    }

    private fun toCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }
}
