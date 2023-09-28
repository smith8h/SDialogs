/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/16/23, 10:23 PM
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

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.CountDownTimer;
import android.view.*;
import android.widget.*;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.*;
import smith.lib.alerts.dialog.callbacks.OnDismissCallback;
import smith.lib.alerts.dialog.utils.SDialogUtils;
import smith.lib.views.recyclerview.SRecyclerView;
import androidx.annotation.*;
import androidx.appcompat.app.AlertDialog;
import java.util.Objects;
import smith.lib.views.scroll.SScrollView;

/**
 * The parent and main class of SDialog lib.
 * this class handles the repeated and main methods of setters and getters for all types of SDialogs.
 * @since 1.0
 */
@SuppressWarnings({"unused"})
public class SDialog {

    protected Context context;
    protected View dialogView;
    protected AlertDialog alertdialog;
    protected DialogBinding b;
    protected SDialogUtils utils;

    protected OnDismissCallback dismissCallback;

    protected int iconColor;
    protected int titleColor;
    protected int textColor;
    protected int iconBackground;
    protected int accentColor = SDialogUtils.COLOR_DEFAULT;
    protected int backgroundColor;
    protected int theme = SDialogUtils.THEME_BY_SYSTEM;
    protected int hintColor;


    protected void init() {
        alertdialog = new AlertDialog.Builder(context).create();
        alertdialog.setView(dialogView);
        alertdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(alertdialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        b = new DialogBinding(dialogView);
        utils = new SDialogUtils(context);
    }

    /**
     * Lock the SDialog to the dismissing when click outside the sdialog window.
     *
     * @param cancelable As {@link Boolean} to lock/ unlock cancel when click outside the border.
     */
    public void setCancelable(boolean cancelable) {
        alertdialog.setCancelable(cancelable);
    }

    /**
     * OnDismissCallBack triggered whenever the sdialog is dismissed.
     *
     * @param callback {@link OnDismissCallback} interface.
     */
    public void setOnDismissCallback(OnDismissCallback callback) {
        this.dismissCallback = callback;
        alertdialog.setOnDismissListener(dialogInterface -> callback.onDismiss());
    }

    /**
     * Set SDialog theme as light, dark or by system.
     *
     * @param theme Options are {@link SDialogUtils#THEME_DARK},
     *              {@link SDialogUtils#THEME_LIGHT} and {@link SDialogUtils#THEME_BY_SYSTEM}.
     */
    public void setTheme(int theme) {
        this.theme = theme;
    }

    /**
     * Set the accent color of current SDialog to create the theme from it.
     *
     * @param color As Int color.
     */
    public void setAccentColor(int color) {
        accentColor = color;
    }

    /**
     * Set the accent color of current SDialog to create the whole theme colors.
     *
     * @param color As string hex color.
     */
    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    /**
     * Show the SDialog.
     */
    public void show() {
        ((Activity) context).runOnUiThread(() -> {
            alertdialog.show();
            utils.animateView(dialogView);
        });
    }

    /**
     * Show the SDialog for a duration of time.
     *
     * @param duration The duration in milliseconds.
     */
    public void show(long duration) {
        ((Activity) context).runOnUiThread(() -> {
            alertdialog.show();
            utils.animateView(dialogView);
            new CountDownTimer(duration, 10) {
                @Override
                public void onTick(long duration) {
                }

                @Override
                public void onFinish() {
                    dismiss();
                }
            }.start();
        });
    }

    /**
     * Dismiss the current displayed SDialog.
     */
    public void dismiss() {
        ((Activity) context).runOnUiThread(() -> alertdialog.dismiss());
    }

    /**
     * Dismiss the current displayed SDialog.
     *
     * @param duration The duration in milliseconds.
     */
    public void dismiss(long duration) {
        ((Activity) context).runOnUiThread(() -> new CountDownTimer(duration, 1) {
            @Override
            public void onTick(long dur) {
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        }.start());
    }

    protected void lightThemeColors() {
        iconColor = utils.darkerColor(accentColor, .2f);
        iconBackground = utils.lighterColor(accentColor, .6f);
        titleColor = utils.darkerColor(accentColor, .2f);
        textColor = utils.darkerColor(accentColor, .35f);
        backgroundColor = utils.lighterColor(accentColor, .9f);
        hintColor = utils.darkerColor(accentColor, .6f);
        accentColor = utils.darkerColor(accentColor, .9f);
    }

    protected void darkThemeColors() {
        iconColor = utils.lighterColor(accentColor, .8f);
        iconBackground = utils.darkerColor(accentColor, .4f);
        titleColor = utils.lighterColor(accentColor, .8f);
        textColor = utils.lighterColor(accentColor, .65f);
        backgroundColor = utils.darkerColor(accentColor, .1f);
        hintColor = utils.lighterColor(accentColor, .4f);
        accentColor = utils.lighterColor(accentColor, .1f);
    }

    protected void updateTheme() {
        switch (theme) {
            case SDialogUtils.THEME_BY_SYSTEM -> {
                if (utils.isNightModeON()) darkThemeColors();
                else lightThemeColors();
            }
            case SDialogUtils.THEME_DARK -> darkThemeColors();
            case SDialogUtils.THEME_LIGHT -> lightThemeColors();
        }
    }

    protected static class DialogBinding {

        final LinearLayout main, holder;
        final ImageView icon, like, dislike, fingerprint;
        final TextView title, text, positive, negative, neutral, percent, fingerprintText, dayTitle, monthTitle, yearTitle;
        final SRecyclerView recycler, dayRV, monthRV, yearRV;
        final TextInputLayout input;
        final TextInputEditText inputed;
        final ProgressBar loading, progress;
        final Slider seek;
        final SScrollView scroll;

        public DialogBinding(@NonNull View view) {
            main = view.findViewById(R.id.sdialog_main);
            holder = view.findViewById(R.id.sdialog_holder);
            icon = view.findViewById(R.id.sdialog_icon);
            title = view.findViewById(R.id.sdialog_title);
            text = view.findViewById(R.id.sdialog_text);
            positive = view.findViewById(R.id.sdialog_positive);
            negative = view.findViewById(R.id.sdialog_negative);
            neutral = view.findViewById(R.id.sdialog_neutral);
            percent = view.findViewById(R.id.sdialog_percent);
            recycler = view.findViewById(R.id.sdialog_recycler);
            dayRV = view.findViewById(R.id.sdialog_day_rv);
            monthRV = view.findViewById(R.id.sdialog_month_rv);
            yearRV = view.findViewById(R.id.sdialog_year_rv);
            dayTitle = view.findViewById(R.id.sdialog_day_title);
            monthTitle = view.findViewById(R.id.sdialog_month_title);
            yearTitle = view.findViewById(R.id.sdialog_year_title);
            input = view.findViewById(R.id.sdialog_input);
            inputed = view.findViewById(R.id.sdialog_inputed);
            loading = view.findViewById(R.id.sdialog_loading);
            progress = view.findViewById(R.id.sdialog_progress);
            seek = view.findViewById(R.id.sdialog_seek);
            like = view.findViewById(R.id.sdialog_like);
            dislike = view.findViewById(R.id.sdialog_dislike);
            scroll = view.findViewById(R.id.sdialog_scroll);
            fingerprint = view.findViewById(R.id.sdialog_fingerprint);
            fingerprintText = view.findViewById(R.id.sdialog_fingerprint_text);
        }
    }
}
