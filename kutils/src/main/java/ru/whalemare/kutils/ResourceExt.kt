package ru.whalemare.kutils
import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * @since 2018
 * @author Anton Vlasov - whalemare
 */
fun Resources.toDp(px: Float): Float {
    return px / (displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 * Decides the device is tablet or not
 * @param inches - device will be detected as tablet, if devise screen be bigger than that. 7 by default
 */
fun Resources.isTablet(inches: Float = 7f): Boolean {
    val metrics = displayMetrics
    val yInches = metrics.heightPixels / metrics.ydpi
    val xInches = metrics.widthPixels / metrics.xdpi
    val diagonalInches = Math.sqrt((xInches * xInches + yInches * yInches).toDouble())
    return diagonalInches >= inches
}
