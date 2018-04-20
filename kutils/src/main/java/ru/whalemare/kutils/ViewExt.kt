package ru.whalemare.kutils
import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView


/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */


inline fun <reified V : View> View.find(@IdRes resId: Int): V {
    return findViewById(resId)
}

fun ViewGroup.forEach(recursive: Boolean = false,
                      body: (v: View) -> Unit) {
    (0 until childCount).forEach { index ->
        val child = getChildAt(index)
        body.invoke(child)
        if (recursive) {
            if (child is ViewGroup) {
                child.forEach(true) {
                    body.invoke(it)
                }
            }
        }
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}

fun View.showKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.keyboard(show: Boolean) {
    if (show) showKeyboard() else hideKeyboard()
}

//fun View?.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT) {
//    if (this == null) {
//        Timber.w("Can`t show snackbar with text id $text, mainView is null")
//    } else {
//        Snackbar.make(this, text, duration).show()
//    }
//}
//
//fun View?.snackbar(@StringRes resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
//    if (this == null) {
//        Timber.w("Can`t show snackbar with text id $resId, mainView is null")
//    } else {
//        snackbar(this.resources.getString(resId), duration)
//    }
//}

fun View.matchScreen(heightRemove: Int = 0) {
    val widthScreen = resources?.displayMetrics!!.widthPixels
    val heightPixels = resources?.displayMetrics!!.heightPixels

//    this.minimumHeight = heightPixels
//    this.minimumWidth = widthScreen

    val params = layoutParams
    params.height = heightPixels - heightRemove
    params.width = widthScreen
    layoutParams = params
}

fun View.setMarginEnd(@DimenRes resId: Int) {
    val offset = this.resources.getDimensionPixelOffset(resId)

    val params = this.layoutParams as LinearLayout.LayoutParams
    params.setMargins(0, 0, offset, 0) //substitute parameters for left, top, right, bottom
    this.layoutParams = params

//    val layoutParams = ViewGroup.MarginLayoutParams(this.layoutParams)
//    layoutParams.rightMargin = offset
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//        layoutParams.marginEnd = offset
//    }
//    this.layoutParams = layoutParams
}

fun View.generateDefaultLayoutParams(): ViewGroup.LayoutParams {
    this.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    return this.layoutParams
}

fun View.gravity(gravity: Int) {
    (this.layoutParams as? FrameLayout.LayoutParams)?.gravity = gravity
    (this.layoutParams as? LinearLayout.LayoutParams)?.gravity = gravity
}

fun View.generateLayoutParams(@DimenRes width: Int, @DimenRes height: Int) {
    this.layoutParams = LinearLayout.LayoutParams(
        resources.getDimensionPixelSize(width),
        resources.getDimensionPixelSize(height)
    )
}

fun View.changeSize(width: Int, height: Int) {
    this.layoutParams.apply {
        this.width = width
        this.height = height
    }
}

fun View.backgroundCompat(@DrawableRes resId: Int) {
    background = ContextCompat.getDrawable(context, resId)
}

fun View.colorCompat(@ColorRes resId: Int) {
    setBackgroundColor(ContextCompat.getColor(context, resId))
}

fun TextView.compatTextColor(@ColorRes resId: Int) {
    setTextColor(resources.getColor(resId))
}


fun View.setMarginBottom(@DimenRes resId: Int) {
    val offset = this.resources.getDimensionPixelOffset(resId)

    if (this.layoutParams == null) {
        generateDefaultLayoutParams()
    }
    val params = this.layoutParams as LinearLayout.LayoutParams
    params.setMargins(0, 0, 0, offset) //substitute parameters for left, top, right, bottom
    this.layoutParams = params

//    val layoutParams = ViewGroup.MarginLayoutParams(this.layoutParams)
//    layoutParams.rightMargin = offset
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//        layoutParams.marginEnd = offset
//    }
//    this.layoutParams = layoutParams
}

fun View.setMarginStartEnd(@DimenRes resId: Int) {
    val offset = this.resources.getDimensionPixelSize(resId)

    val params = this.layoutParams as LinearLayout.LayoutParams
    params.setMargins(offset, 0, offset, 0) //substitute parameters for left, top, right, bottom
    this.layoutParams = params
}

fun View.animateVisible() {
    val view = this
    animate().alpha(1f)
        .setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {
                if (view.visibility == View.VISIBLE) {
                    view.visibility = View.GONE
//                        view.visibility = View.INVISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.VISIBLE
            }
        })
        .start()
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    if (this.visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.visibility(visible: Boolean, animated: Boolean = false) {
    if (animated) {
        if (visible) animateShow() else animateGone()
    } else {
        if (visible) visible() else gone()
    }
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.animateShow() {
    visible()
    val currentAlpha = alpha
    ValueAnimator.ofFloat(currentAlpha, 1f)?.also {
        it.addUpdateListener { value ->
            val animatedValue = value.animatedValue as Float
            alpha = animatedValue
        }
        it.duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    }!!.start()
}

fun View.animateGone() {
    val currentAlpha = alpha
    ValueAnimator.ofFloat(currentAlpha, 0f)?.also {
        it.addUpdateListener { value ->
            val animatedValue = value.animatedValue as Float
            alpha = animatedValue

            if (animatedValue == 0f) gone()
        }
        it.duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    }!!.start()
}

fun View.setHeight(height: Int) {
    layoutParams.height = height
}

fun ScrollView.disableTouchTheft() {
    var prevScrollY: Int = 0
    var inScopeTounch = false

    setOnTouchListener { view, motionEvent ->
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
//                inScopeTounch = true
//                if (prevScrollY == getScrollRange()) {
//                    prevScrollY = getScrollRange() - 1
//                    scrollTo(0, prevScrollY)
//                } else if (prevScrollY == 0) {
//                    prevScrollY += 1
//                    scrollTo(0, prevScrollY)
//                }
            }
            MotionEvent.ACTION_MOVE -> {
                if ((prevScrollY == getScrollRange() && scrollY == getScrollRange())
                    || (prevScrollY == 0 && scrollY == 0)) {
                    (view.parent as ViewGroup).onTouchEvent(motionEvent)
                    prevScrollY = scrollY
                    return@setOnTouchListener true
                }
            }
            MotionEvent.ACTION_UP -> {
//                inScopeTounch = false
//
//                if (prevScrollY == getScrollRange()) {
//                    prevScrollY = getScrollRange() - 1
//                    scrollTo(0, prevScrollY)
//                } else if (prevScrollY == 0) {
//                    prevScrollY += 1
//                    scrollTo(0, prevScrollY)
//                }
            }
        }

//        if (inScopeTounch) {

//        }

        prevScrollY = scrollY
        false
    }
}