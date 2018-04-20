package ru.whalemare.kutils
import android.view.Surface
import android.view.WindowManager

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
fun WindowManager.isPortrait() = defaultDisplay?.rotation == Surface.ROTATION_0