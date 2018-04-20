package ru.whalemare.kutils

import java.util.*

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun Calendar.toDate() = Date(timeInMillis).apply {
    timeZone = TimeZone.getTimeZone("UTC")
}