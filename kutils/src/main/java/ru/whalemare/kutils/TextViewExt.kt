package ru.whalemare.kutils
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.widget.TextView

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */

/**
 * Remove all drawables from [TextView] and set only to left
 */
fun TextView.setDrawableLeft(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0)
}

/**
 * Remove all drawables from [TextView] and set only to right
 */
fun TextView.setDrawableRight(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0)
}

/**
 * Remove all drawables from [TextView] and set only to bottom
 */
fun TextView.setDrawableBottom(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resId)
}

/**
 * Remove all drawables from [TextView] and set only top
 */
fun TextView.setDrawableTop(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0)
}

fun TextView.textColorCompat(@ColorRes resId: Int) {
    setTextColor(ContextCompat.getColor(context, resId))
}