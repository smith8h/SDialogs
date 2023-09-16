/*
 *
 *   Created by Dev. Smith (Hussein Shakir) on 9/16/23, 10:07 PM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 *   Last modified: 8/16/23, 3:52 PM
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *                     You may obtain a copy of the License at
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * /
 */

package smith.lib.alerts.dialog.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.*;
import android.graphics.Color;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.TypedValue;
import android.view.*;
import androidx.annotation.*;

@SuppressWarnings({"unused", "all"})
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

    /**
     * Get a darker color from existing one.
     *
     * @param color  the color you want to get a darker degree of it.
     * @param factor the factor to make the color darker or less darker, values are a range of 0.0f-1.0f.
     * @return the new darker color from that original one using the factor as degree of brightness.
     */
    public int darkerColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha,
                Math.max((int) (red * factor), 0),
                Math.max((int) (green * factor), 0),
                Math.max((int) (blue * factor), 0));
    }

    /**
     * Get a lighter color from existing one.
     *
     * @param color  the color you want to get a lighter degree of it.
     * @param factor the factor to make the color lighter or less lighter, values are a range of 0.0f-1.0f.
     * @return the new lighter color from that original one using the factor as degree of brightness.
     */
    public int lighterColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Convert pixels to its equivalent density dependent pixels.
     *
     * @param dp Int value of density pixels.
     * @return Returns Int value of dp.
     */
    public int dp(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );
    }

    /**
     * Create the progress drawable for {@link smith.lib.alerts.dialog.ProgressSDialog}.
     * this progress will only be shown in SDK 29 and up..
     *
     * @param progressColor   the foreground color (the progress track).
     * @param backgroundColor the background color (the track background).
     * @return progress layer list as drawable.
     */
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
