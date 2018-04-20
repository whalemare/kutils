package ru.whalemare.kutils
import android.widget.ScrollView


/**
 * @since 2018
 * @author Anton Vlasov - whalemare
 */

/**
 * Return max scroll range for [ScrollView]
 */
fun ScrollView.getScrollRange(): Int {
    val mPaddingBottom = 0
    val mPaddingTop = 0

    var scrollRange = 0
    if (childCount > 0) {
        val child = getChildAt(0)
        scrollRange = Math.max(0, child.height - (height - mPaddingBottom - mPaddingTop))
    }

    return scrollRange
}

fun ScrollView.addOnScrollChangedListener(listener: (x: Int, y: Int) -> Unit) {
    viewTreeObserver.addOnScrollChangedListener {
        listener.invoke(scrollX, scrollY)
    }
}