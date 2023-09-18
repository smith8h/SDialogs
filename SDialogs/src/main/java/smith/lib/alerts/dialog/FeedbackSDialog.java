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
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.util.Objects;

import smith.lib.alerts.dialog.callbacks.OnFeedbackSubmitCallBack;

/**
 * The beautiful and fastest class of SDialog lib.
 * this class create a feedback SDialogs that let the user suggests the most liked or unliked things on your app.
 */
@SuppressWarnings({"unused"})
public class FeedbackSDialog extends SDialog {

    private boolean isLiked;

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public FeedbackSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_feedback, null);
        init();

        b.like.setOnClickListener(v -> {
            b.input.setVisibility(View.VISIBLE);
            b.negative.setVisibility(View.VISIBLE);
            updateButton(b.like, iconBackground, iconColor);
            updateButton(b.dislike, iconColor, iconBackground);
            isLiked = true;
        });

        b.dislike.setOnClickListener(v -> {
            b.input.setVisibility(View.VISIBLE);
            b.negative.setVisibility(View.VISIBLE);
            updateButton(b.dislike, iconBackground, iconColor);
            updateButton(b.like, iconColor, iconBackground);
            isLiked = false;
        });
    }

    /**
     * Set icon for the current SDialog.
     * @param icon As Int resource (R.drawable.icon).
     */
    public void setIconResource(@DrawableRes int icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageResource(icon);
    }

    /**
     * Set icon for the current SDialog.
     * @param icon As {@link Drawable}.
     */
    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageDrawable(icon);
    }

    /**
     * Set icon for the current SDialog.
     * @param icon As {@link Bitmap}.
     */
    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageBitmap(icon);
    }

    /**
     * Set title from string to current SDialog.
     * @param title String title.
     */
    public void setTitle(String title) {
        b.title.setText(title);
    }

    /**
     * Set text from int resource to current SDialog.
     * @param resTitle Int resource title (R.string.title).
     */
    public void setTitle(@StringRes int resTitle) {
        b.title.setText(resTitle);
    }

    /**
     * Set text from {@link String} to current FeedbackSDialog.
     * @param text As {@link String} text.
     */
    public void setText(String text) {
        b.text.setText(text);
    }

    /**
     * Set text from int resource to current SDialog.
     * @param text Int resource text (R.string.text).
     */
    public void setText(@StringRes int text) {
        b.text.setText(text);
    }

    /**
     * The hint displayed inside the text input field.
     * @param hint Text as {@link String}
     */
    public void setInputFieldHint(String hint) {
        b.input.setHint(hint);
    }

    /**
     * Add a functionality to submit feedback interface.
     * @param text a text to be displayed on submit button.
     * @param callBack a functional submit callback using {@link OnFeedbackSubmitCallBack}
     */
    public void setSubmitFeedBackButton(String text, OnFeedbackSubmitCallBack callBack) {
        b.negative.setText(text);
        b.negative.setOnClickListener(v -> {
            callBack.onSubmit(isLiked, Objects.requireNonNull(b.inputed.getText()).toString());
            dismiss();
        });
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
        super.show(duration);
    }

    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        updateButton(b.like, iconColor, iconBackground);
        updateButton(b.dislike, iconColor, iconBackground);
        b.negative.setTextColor(iconColor);
        utils.backgroundColor(b.negative, iconBackground);

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

    private void updateButton(@NonNull ImageView view, int color, int background) {
        view.setColorFilter(color);
        utils.backgroundColor(view, background);
    }
}
