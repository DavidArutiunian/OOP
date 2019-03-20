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
        this.string += NULL_CHAR
    }

    constructor(string: Array<Char>, length: Int) : this() {
        this.length = length
        this.string = string
        this.string += NULL_CHAR
    }

    constructor(other: MyString) : this() {
        this.length = other.length
        this.string = other.string
    }

    constructor(other: String) : this() {
        this.length = other.length
        this.string = Array(this.length) { i -> other[i] }
        this.string += NULL_CHAR
    }

    fun getLength(): Int {
        return length
    }

    fun getStringData(): Array<Char> {
        return string
    }

    fun substring(start: Int, length: Int): MyString {
        if (start + length > this.length || start + length < start) {
            throw StringIndexOutOfBoundsException("Begin $start, end $${start + length}, length ${this.length}")
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
}
