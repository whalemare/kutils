package ru.whalemare.kutils
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */

inline fun <reified T : Activity> Context.startActivity(
    bundle: Bundle = Bundle(),
    clearBackstack: Boolean
) {
    val intent = Intent(this, T::class.java).apply {
        putExtras(bundle)
        if (clearBackstack) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
    startActivity(intent)
    if (clearBackstack && this is Activity) {
        ActivityCompat.finishAffinity(this)
    }
}

inline fun <reified T : Activity> Context.startActivity(bundle: Bundle = Bundle()) {
    startActivity<T>(bundle = bundle, clearBackstack = false)
}

fun Context.message(text: CharSequence) {
    if (this is Activity) {
        val root = findViewById<ViewGroup>(android.R.id.content)
//        root.snackbar(text)
    } else {
        toast(text.toString())
    }
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

inline fun <reified V : View> Activity.find(@IdRes resId: Int): V {
    return findViewById<V>(resId) as V
}

fun Context.compatColor(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun Context.compatDrawable(resId: Int): Drawable {
    return ContextCompat.getDrawable(this, resId)!!
}

fun Context.getWindowManager(): WindowManager =
    getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.getDisplay(): Display = getWindowManager().defaultDisplay

/**
 * The absolute width of the available display size in pixels.
 */
fun Context.getDisplayWidth(): Int {
    val metrics = DisplayMetrics()
    getDisplay().getMetrics(metrics)
    return metrics.widthPixels
}

fun Context.isTablet(inches: Float = 7f): Boolean {
    val metrics = DisplayMetrics()
    getDisplay().getMetrics(metrics)

    val yInches = metrics.heightPixels / metrics.ydpi
    val xInches = metrics.widthPixels / metrics.xdpi
    val diagonalInches = Math.sqrt((xInches * xInches + yInches * yInches).toDouble())
    return diagonalInches >= inches
}

fun Context.isPhone() = !isTablet()




