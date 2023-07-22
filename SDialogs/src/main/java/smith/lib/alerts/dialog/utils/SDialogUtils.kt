package smith.lib.alerts.dialog.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.*
import android.graphics.Color
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.annotation.RequiresApi

class SDialogUtils(var context: Context) {

    /**
     * Animate Views with property Alpha.
     *
     * @param view Pass any view to animate it.
     */
    fun animateView(view: View) {
        view.alpha = 0f
        val anim = ObjectAnimator()
        anim.duration = 620
        anim.setFloatValues(0f, 1f)
        anim.setPropertyName("alpha")
        anim.target = view
        anim.start()
    }

    fun nightModeON(): Boolean {
        val flags: Int = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flags == Configuration.UI_MODE_NIGHT_YES
    }

    fun backgroundColor(view: View, color: Int) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(color)
        gradientDrawable.cornerRadius = dp(24).toFloat()

        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(color), gradientDrawable, null)

        view.background = rippleDrawable
    }

    fun darkerColor(color: Int, factor: Float): Int {
        val alpha = Color.alpha(color)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(
            alpha,
            (red * factor).toInt().coerceAtLeast(0),
            (green * factor).toInt().coerceAtLeast(0),
            (blue * factor).toInt().coerceAtLeast(0)
        )
    }

    fun lighterColor(color: Int, factor: Float): Int {
        val alpha = Color.alpha(color)
        val red = ((Color.red(color) * (1 - factor) / 255 + factor) * 255).toInt()
        val green = ((Color.green(color) * (1 - factor) / 255 + factor) * 255).toInt()
        val blue = ((Color.blue(color) * (1 - factor) / 255 + factor) * 255).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    /**
     * Convert pixels to its related density dependent pixels.
     *
     * @param dp Int value of density pixels.
     * @return Returns Int value of dp.
     */
    fun dp(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun createProgressLayerList(progressColor: Int, backgroundColor: Int): Drawable {
        val cornerRadius = SDialogUtils(context).dp(6).toFloat()

        val backgroundShape = RoundRectShape(
            floatArrayOf(
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius
            ),
            null, null
        )

        val backgroundDrawable = ShapeDrawable(backgroundShape)
        backgroundDrawable.paint.color = backgroundColor

        val progressShape = RoundRectShape(
            floatArrayOf(
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius,
                cornerRadius
            ),
            null, null
        )

        val progressDrawable = ShapeDrawable(progressShape)
        progressDrawable.paint.color = progressColor

        // Set the layer list
        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = backgroundDrawable
        layers[1] = ScaleDrawable(progressDrawable, Gravity.START, 1f, -1f)

        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setId(0, android.R.id.background)
        layerDrawable.setId(1, android.R.id.progress)

        return layerDrawable
    }
}