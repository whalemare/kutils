package ru.whalemare.kutils
/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun MutableMap<Any, List<Any>>.toFlatList(): List<Any> {
    val list = mutableListOf<Any>()

    this.forEach { (key, values) ->
        list.add(key)
        list.addAll(values)
    }

    return list
}
