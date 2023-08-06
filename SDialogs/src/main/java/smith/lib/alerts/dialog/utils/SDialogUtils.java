package smith.lib.alerts.dialog.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import smith.lib.tools.color.SColor;

public class SDialogUtils {

    private final Context context;

    public SDialogUtils(Context context) {
        this.context = context;
    }

    /**
     * Animate Views with property Alpha.
     *
     * @param view Pass any view to animate it.
     */
    public void animateView(@NonNull View view) {
        view.setAlpha(0f);
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                .setDuration(620)
                .start();
    }

    /**
     * Check if devise dark mode enabled.
     *
     * @return true if dark mode is enabled.
     */
    public boolean nightModeON() {
        var flags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return flags == Configuration.UI_MODE_NIGHT_YES;
    }

    /**
     * Set a Drawable background with round corners by 24dp.
     */
    public void backgroundColor(@NonNull View view, int color) {
        var gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(dp(24));

        var rippleDrawable = new RippleDrawable(ColorStateList.valueOf(color), gradientDrawable, null);

        view.setBackground(rippleDrawable);
    }

    public int darkerColor(int color, float factor) {
        return SColor.darkerColor(color, factor);
    }

    public int lighterColor(int color, float factor) {
        return SColor.lighterColor(color, factor);
    }

    /**
     * Convert pixels to its equivalent density dependent pixels.
     *
     * @param dp Int value of density pixels.
     * @return Returns Int value of dp.
     */
    public float dp(int dp) {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.getResources().getDisplayMetrics()
        );
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    public Drawable createProgressLayerList(int progressColor, int backgroundColor) {
        var cornerRadius = dp(6);

        var backgroundShape = new RoundRectShape(
                new float[]{
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius,
                        cornerRadius
                },
                null, null
        );

        var backgroundDrawable = new ShapeDrawable(backgroundShape);
        backgroundDrawable.getPaint().setColor(backgroundColor);

        var progressShape = new RoundRectShape(
            new float[]{
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius
            },
            null, null
        );

        var progressDrawable = new ShapeDrawable(progressShape);
        progressDrawable.getPaint().setColor(progressColor);

        // Set the layer list
        var layers = new Drawable[2];
        layers[0] = backgroundDrawable;
        layers[1] = new ScaleDrawable(progressDrawable, Gravity.START, 1f, -1f);

        var layerDrawable = new LayerDrawable(layers);
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.progress);

        return layerDrawable;
    }
}