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
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.*;

import smith.lib.alerts.dialog.callbacks.OnClickCallback;
import smith.lib.alerts.dialog.callbacks.OnProgressCallback;

/**
 * The beautiful and smoothest class of SDialog lib.
 * this class create a progress loading SDialogs that inform the user about the progress of the task
 * going on background.
 * @since 1.0
 */
@SuppressWarnings({"unused"})
public class ProgressSDialog extends SDialog {
    
    private OnProgressCallback callback = null;

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public ProgressSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_progress, null);
        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b.progress.setMin(0);
        }
        b.progress.setMax(100);
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
     * Set th min value to the progress, by default is 0 for sdk 25 and bellow.
     * @param min the minimum no. to start the progress from, as {@link Integer}.
     */
    public void setMin(int min) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            b.progress.setMin(min);
        }
    }

    /**
     * Set th max value to the progress.
     * @param max the maximum no. to end the progress at, as {@link Integer}.
     */
    public void setMax(int max) {
        b.progress.setMax(max);
    }

    /**
     * Set the current progress to the SDialog.
     * @param progress the progress as {@link Integer}.
     */
    public void setProgress(int progress) {
        b.progress.setProgress(progress);
        setProgressText();
        if (getProgress() == getMax()) {
            dismiss();
            if (callback != null) callback.onFinish();
        }
    }
    
    private void setProgressText() {
        int percent = (getProgress() * 100) / getMax();
        String info = getProgress() + "/" + getMax() + " (" + percent + "%)";
        b.percent.setText(info);
        if (callback != null) callback.onProgress(getProgress(), percent);
    }

    /**
     * Add a functional interface to get a callback whenever the progress changed.
     * @param callback a callback using {@link OnProgressCallback}.
     */
    public void setOnProgressCallback(OnProgressCallback callback) {
        this.callback = callback;
    }

    /**
     * Set the negative button text and a functional interface to it.
     * @param text a string text to display on the negative button.
     * @param clickCallback a callback using {@link OnClickCallback}.
     */
    @SuppressLint("NewApi")
    public void setNegativeButtonAction(String text, OnClickCallback clickCallback) {
        b.negative.setVisibility(View.VISIBLE);
        b.negative.setText(text);
        b.negative.setOnClickListener(v -> {
            clickCallback.onClick();
            this.callback = null;
            b.progress.setProgress(getMin());
            dismiss();
        });
    }

    /**
     * Get the current progress.
     * @return an int value of the current progress.
     */
    public int getProgress() {
        return b.progress.getProgress();
    }

    /**
     * Get the min value set to the SDialog. requires a minimum sdk 26 and up.
     * @return an integer value represent the minimum value set to the SDialog.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getMin() {
        return b.progress.getMin();
    }

    /**
     * Get the max value set to the SDialog.
     * @return an integer value represent the maximum value set to the SDialog.
     */
    public int getMax() {
        return b.progress.getMax();
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
        setCancelable(false);
        update();
        super.show();
    }
    
    @Override
    public void show(long duration) {
        setCancelable(false);
        update();
        super.show();
    }
        
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.percent.setTextColor(textColor);
        b.negative.setTextColor(iconColor);
        utils.backgroundColor(b.negative, iconBackground);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            b.progress.setProgressDrawable(utils.createProgressLayerList(accentColor, iconBackground));
        else
            b.progress.setProgressTintList(ColorStateList.valueOf(accentColor));
    }
}
