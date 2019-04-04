package labs.lab7.find_max_ex

fun <T : Comparable<T>, L : (max: T, item: T) -> Boolean> findMax(array: Array<T>, less: L? = null): T? {
    var max: T? = null
    for (item in array) {
        if (max == null) {
            max = item
            continue
        }
        if (less != null) {
            if (less(max, item)) {
                max = item
            }
        } else if (item is String && max is String) {
            if (compareStringsLexicographically(max, item) > 0) {
                max = item
            }
        } else {
            max = getMaxFrom(item, max)
        }
    }
    return max
}

fun <T, L : (max: T, item: T) -> Boolean> findMax(array: Array<T>, less: L? = null): T? {
    var max: T? = null
    for (item in array) {
        if (max == null) {
            max = item
            continue
        }
        if (less != null) {
            if (less(max, item)) {
                max = item
            }
        } else if (item is String && max is String && compareStringsLexicographically(max, item) > 0) {
            max = item
        }
    }
    return max
}

internal fun <T : Comparable<T>> getMaxFrom(left: T, right: T?): T {
    return if (right == null || left > right) {
        left
    } else {
        right
    }
}

internal fun compareStringsLexicographically(left: String, right: String): Int {
    var i = 0
    while (i < left.length && i < right.length) {
        if (left[i].toInt() == right[i].toInt()) {
            i++
            continue
        } else {
            return left[i].toInt() - right[i].toInt()
        }
    }

    return when {
        left.length < right.length -> left.length - right.length
        left.length > right.length -> left.length - right.length
        else -> 0
    }
}
