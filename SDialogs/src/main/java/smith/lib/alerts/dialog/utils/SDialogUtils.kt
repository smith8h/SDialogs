package smith.lib.alerts.dialog.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.*
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.annotation.RequiresApi
import smith.lib.tools.color.SColor

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

    /**
     * Check if devise dark mode enabled.
     *
     * @return true if dark mode is enabled.
     */
    fun nightModeON(): Boolean {
        val flags: Int = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flags == Configuration.UI_MODE_NIGHT_YES
    }

    /**
     * Set a Drawable background with round corners by 24dp.
     */
    fun backgroundColor(view: View, color: Int) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(color)
        gradientDrawable.cornerRadius = dp(24).toFloat()

        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(color), gradientDrawable, null)

        view.background = rippleDrawable
    }

    fun darkerColor(color: Int, factor: Float): Int {
        return SColor.darkerColor(color, factor)
    }

    fun lighterColor(color: Int, factor: Float): Int {
        return SColor.lighterColor(color, factor)
    }

    /**
     * Convert pixels to its equivalent density dependent pixels.
     *
     * @param dp Int value of density pixels.
     * @return Returns Int value of dp.
     */
    private fun dp(dp: Int): Int {
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