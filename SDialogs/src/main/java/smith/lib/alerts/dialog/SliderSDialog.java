/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/16/23, 10:13 PM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import com.google.android.material.slider.Slider;
import android.graphics.*;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import smith.lib.alerts.dialog.callbacks.OnSlideCallback;

/**
 * The beautiful and coolest class of SDialog lib.
 * this class create a seekbar SDialogs that let the user select from range of numerical values.
 */
@SuppressWarnings({"unused"})
public class SliderSDialog extends SDialog {

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public SliderSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_seek, null);
        init();

        b.seek.setValueFrom(0);
        b.seek.setValueTo(100);

        int height = b.seek.getTrackHeight();
        float newHeight = height * 2.5f;
        b.seek.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override public void onStartTrackingTouch(@NonNull Slider slider) { slider.setTrackHeight((int) newHeight); }
            @Override public void onStopTrackingTouch(@NonNull Slider slider) { slider.setTrackHeight(height); }
        });
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As Int resource (R.drawable.icon).
     */
    public void setIconResource(@DrawableRes int icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageResource(icon);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As {@link Drawable}.
     */
    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageDrawable(icon);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As {@link Bitmap}.
     */
    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageBitmap(icon);
    }

    /**
     * Set title from string to current SDialog.
     *
     * @param title String title.
     */
    public void setTitle(String title) {
        b.title.setText(title);
    }

    /**
     * Set text from int resource to current SDialog.
     *
     * @param resTitle Int resource title (R.string.title).
     */
    public void setTitle(@StringRes int resTitle) {
        b.title.setText(resTitle);
    }

    /**
     * Set progress text to current SDialog.
     *
     * @param text {@link String} text.
     */
    public void setText(String text) {
        b.text.setText(text);
    }

    /**
     * Set text from int resource to current SDialog.
     *
     * @param text Int resource text (R.string.text).
     */
    public void setText(@StringRes int text) {
        b.text.setText(text);
    }

    /**
     * Set th min value to the slider.
     * @param min the minimum no. to start the slider from, as {@link Integer}.
     */
    public void setMin(int min) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b.seek.setValueFrom(min);
        }
    }

    /**
     * Set th max value to the slider.
     * @param max the maximum no. to end the slider at, as {@link Integer}.
     */
    public void setMax(int max) {
        b.seek.setValueTo(max);
    }

    /**
     * Set the value to slider by default.
     * @param value an int value represent the default value set by the slider.
     */
    public void setValue(float value) {
        b.seek.setValue(value);
    }

    /**
     * Set the step to go through values.
     * @param stepBy a steps that the slider will go through.
     */
    public void setStepBy(float stepBy) {
    	b.seek.setStepSize(stepBy);
    }

    /**
     * Set a functional interface to the positive button.
     * @param text string text that displayed on the button.
     * @param callback a callback using {@link OnSlideCallback}.
     */
    public void setPositiveButtonAction(String text, OnSlideCallback callback) {
        b.positive.setText(text);
        b.positive.setOnClickListener(v-> {
            callback.onValueSelected(b.seek.getValue());
            dismiss();
        });
    }

    /**
     * @param text string text that displayed on the button.
     */
    public void setNegativeButtonText(String text) {
        b.negative.setText(text);
        b.negative.setOnClickListener(v-> dismiss());
    }

    /**
     * Get the current value.
     * @return an int value of the current slider value.
     */
    public float getValue() {
        return b.seek.getValue();
    }

    /**
     * Get the min value set to the SDialog.
     * @return an integer value represent the minimum value set to the SDialog.
     */
    public float getMinValue() {
        return b.seek.getValueFrom();
    }

    /**
     * Get the max value set to the SDialog.
     * @return an integer value represent the maximum value set to the SDialog.
     */
    public float getMaxValue() {
        return b.seek.getValueTo();
    }

    /**
     * @return Accent color of current SDialog showed as light theme or dark theme.
     */
    public int getAccentColor() {
        return accentColor;
    }

    /**
     * @return Title color of current SDialog showed as light theme or dark theme.
     */
    public int getTitleColor() {
        return titleColor;
    }

    /**
     * @return Background color of current SDialog showed as light theme or dark theme.
     */
    public int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @return Text color of AlertSDialog as displayed as Dark or Light Theme.
     */
    public int getTextColor() {
        return textColor;
    }
    
    @Override
    public void show() {
        update();
        super.show();
    }
    
    @Override
    public void show(long duration) {
        update();
        super.show();
    }
    
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.positive.setTextColor(accentColor);
        b.negative.setTextColor(accentColor);
        
        int[][] states = new int[][] {
            new int[] { android.R.attr.state_active },
            new int[] { android.R.attr.state_hovered },
            new int[] { android.R.attr.state_enabled },
            new int[] { -android.R.attr.state_active }
        };
        int[] colors = new int[] {accentColor, accentColor, accentColor, hintColor};
        
        b.seek.setThumbTintList(ColorStateList.valueOf(accentColor));
        b.seek.setTrackTintList(new ColorStateList(states, colors));
    }
}
