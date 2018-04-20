package ru.whalemare.kutils
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */

/**
 * It`s getting textView title from toolbar by reflection.
 * Be carefully
 */
fun Toolbar.getTitleView(): TextView {
    val field = this.javaClass.getDeclaredField("mTitleTextView")
    return field?.also {
        it.isAccessible = true
    }?.get(this) as TextView
}

fun Toolbar.getNavigationView(): ImageView {
    val field = this.javaClass.getDeclaredField("mNavButtonView")
    return field?.also {
        it.isAccessible = true
    }?.get(this) as ImageView
}