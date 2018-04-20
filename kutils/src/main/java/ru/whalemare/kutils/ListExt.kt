package ru.whalemare.kutils
import java.util.*
import kotlin.collections.ArrayList

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun <T> MutableList<T>.poll(): T {
    val iterator = this.iterator()
    if (!iterator.hasNext()) {
        throw NoSuchElementException()
    }

    val item = iterator.next()
    iterator.remove()
    return item
}

fun MutableList<String>.addIsNotBlank(string: String) {
    if (string.isNotBlank()) this.add(string)
}

fun MutableList<Pair<String, String>>.addIsNotBlank(pair: Pair<String, String>) {
    if (pair.first.isNotBlank() && pair.second.isNotBlank()) this.add(pair)
}

fun <T> List<T>.copy(): MutableList<T> {
    val list = mutableListOf<T>()
    list.addAll(this)
    return list
}

fun <T> List<T>.addFirst(element: T): List<T> {
    val list = ArrayList<T>(this.size + 1)
    list.add(element)
    list.addAll(this)
    return list
}

fun <T> MutableList<T>.removeLast(): T {
    return this.removeAt(this.size - 1)
}

fun <D : Any> List<D>.groupByPairs(): List<Pair<D, D?>> {
    val list = mutableListOf<Pair<D, D?>>()

    var i = 0
    while (i < this.size) {
        val left = i
        val right = left + 1

        val leftValue = this[left]
        val rightValue = this.getOrNull(right)

        list.add(Pair(leftValue, rightValue))
        i = (right + 1)
    }

    return list
}