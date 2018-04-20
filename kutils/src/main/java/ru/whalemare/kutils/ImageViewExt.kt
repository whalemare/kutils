package ru.whalemare.kutils
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.widget.ImageView

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun ImageView.compatDrawable(@DrawableRes drawableRes: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}
