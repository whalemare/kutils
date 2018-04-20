package ru.whalemare.kutils
/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun <D : Any> HashSet<D>.groupByPairs(): List<Pair<D, D?>> {
    val hashList = this.toList()
    return hashList.groupByPairs()
}