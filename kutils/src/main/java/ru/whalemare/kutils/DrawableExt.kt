package ru.whalemare.kutils
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun Drawable.toBitmap(): Bitmap? {
    var bitmap: Bitmap? = null

    if (this is BitmapDrawable) {
        if (this.bitmap != null) {
            return this.bitmap
        }
    }

    if (this.intrinsicWidth <= 0 || this.intrinsicHeight <= 0) {
        bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
    } else {
        bitmap = Bitmap.createBitmap(this.intrinsicWidth, this.intrinsicHeight, Bitmap.Config.ARGB_8888)
    }

    val canvas = Canvas(bitmap!!)
    this.setBounds(0, 0, canvas.width, canvas.height)
    this.draw(canvas)
    return bitmap
}
