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
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import java.util.Objects;
import smith.lib.alerts.dialog.callbacks.OnInputClickCallBack;

/**
 * The beautiful and well-designed class of SDialog lib.
 * this class create an input field SDialogs that let the user input some info you want to collect.
 * @since 1.0
 */
@SuppressWarnings({"unused"})
public class InputSDialog extends SDialog {

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public InputSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.sdialog_input, null);
        init();
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
     * Set text from {@link String} or {@link CharSequence} to current SDialog.
     *
     * @param text {@link CharSequence} text.
     */
    public void setText(CharSequence text) {
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
     * Declare displayed positive button text and its functionality.
     * @param text     String represent the text of positive button (e.g. "Confirm")
     * @param callback Click callback using {@link OnInputClickCallBack}
     */
    public void setPositiveButtonAction(String text, OnInputClickCallBack callback) {
        b.positive.setText(text);
        b.positive.setOnClickListener(v -> {
            callback.onClick(Objects.requireNonNull(b.inputed.getText()).toString());
            dismiss();
        });
    }

    /**
     * Declare displayed negative button text.
     * @param text String represent the text of negative button (e.g. "Cancel")
     */
    public void setNegativeButtonText(String text) {
        b.negative.setText(text);
        b.negative.setOnClickListener(v -> dismiss());
    }

    /**
     * The hint displayed inside the text input field.
     * @param hint Text as {@link String}
     */
    public void setInputFieldHint(String hint) {
        b.input.setHint(hint);
    }

    /**
     * The text in the text input field.
     * @param text As {@link String}
     */
    public void setInputFieldText(String text) {
        Objects.requireNonNull(b.input.getEditText()).setText(text);
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

    /**
     * @return Hint color of AlertSDialog as displayed as Dark or Light Theme.
     */
    public int getHintColor() {
        return hintColor;
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

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_hovered},
                new int[]{android.R.attr.state_enabled},
                new int[]{}
        };
        int[] colors = new int[]{ accentColor, accentColor, accentColor, hintColor };

        b.input.setBoxStrokeColorStateList(new ColorStateList(states, colors));
        b.input.setDefaultHintTextColor(new ColorStateList(states, colors));
        b.input.setHintTextColor(new ColorStateList(states, colors));
        b.inputed.setTextColor(textColor);
        b.inputed.setHintTextColor(hintColor);
    }
}
