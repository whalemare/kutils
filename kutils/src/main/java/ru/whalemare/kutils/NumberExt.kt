package ru.whalemare.kutils
import android.content.res.Resources



/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun Int.toPx(): Int {
    return Math.round(Resources.getSystem().displayMetrics.density * this)
}

fun Int.toDp(): Int {
    return Math.round(this / Resources.getSystem().displayMetrics.density)
}

fun Float.toPx(): Float {
    return Resources.getSystem().displayMetrics.density * this
}

fun Float.toDp(): Float {
    return this / Resources.getSystem().displayMetrics.density
}