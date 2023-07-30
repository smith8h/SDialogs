package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.*;
import android.widget.*;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.*;
import kotlin.Suppress;
import smith.lib.alerts.dialog.callbacks.OnDismissCallBack;
import smith.lib.alerts.dialog.utils.SDialogUtils;
import smith.lib.views.recyclerview.SRecyclerView;
import androidx.annotation.*;
import androidx.appcompat.app.AlertDialog;
import java.util.Objects;
import co.encept.patternlockview.PatternLockView;

/**
 * The parent and main class of SDialog lib.
 * this class handles the repeated and main methods of setters and getters for all types of SDialogs.
 */
@Suppress(names = {"Unused"})
public class SDialog {

    /**
     * The default color suggested for use in SDialogs.
     */
    public static final int COLOR_DEFAULT = 0xFFA7B4C5;

    /**
     * The light or dark theme setting of SDialogs, based on device's settings.
     */
    public static final int THEME_BY_SYSTEM = 0;
    /**
     * The dark theme setting of SDialogs.
     */
    public static final int THEME_DARK = 1;
    /**
     * The light theme setting of SDialogs.
     */
    public static final int THEME_LIGHT = 2;

    /**
     * The constant key of id of items in {@link  MultiSelectSDialog}.
     * Used to return the id of checked items.
     */
    public static final String KEY_ITEM_ID = "id";
    /**
     * The constant key of text of items in {@link  MultiSelectSDialog}.
     * Used to return the text string of checked items.
     */
    public static final String KEY_ITEM_TEXT = "text";
    /**
     * The constant key of checked items in {@link  MultiSelectSDialog}.
     * Used to return the condition of checked items.
     */
    public static final String KEY_ITEM_CHECKED = "checked";

    /**
     * The correct mode of pattern drew in PatternSDialog.
     * Will show the green color to refer to correct pattern.
     */
    public static final int PATTERN_MODE_CORRECT = PatternLockView.PatternViewMode.CORRECT;
    /**
     * The wrong mode of pattern drew in PatternSDialog.
     * Will show the red color to refer to wrong pattern.
     */
    public static final int PATTERN_MODE_WRONG = PatternLockView.PatternViewMode.WRONG;

    protected Context context;
    protected View dialogView;
    protected AlertDialog alertdialog;
    protected DialogBinding b;
    protected SDialogUtils utils;

    protected int iconColor;
    protected int titleColor;
    protected int textColor;
    protected int iconBackground;
    protected int accentColor = COLOR_DEFAULT;
    protected int backgroundColor;
    protected int theme = THEME_BY_SYSTEM;
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
     * @param cancelable As {@link Boolean} to lock/ unlock cancel when click outside the border.
     */
    public void setCancelable(boolean cancelable) {
        alertdialog.setCancelable(cancelable);
    }

    /**
     * OnDismissCallBack triggered whenever the sdialog is dismissed.
     * @param callback {@link OnDismissCallBack} interface.
     */
    public void setOnDismissCallBack(OnDismissCallBack callback) {
        alertdialog.setOnDismissListener(dialogInterface -> callback.onDismiss());
    }

    /**
     * Set SDialog theme as light, dark or by system.
     *
     * @param theme Options are {@link SDialog#THEME_DARK},
     *              {@link SDialog#THEME_LIGHT} and {@link SDialog#THEME_BY_SYSTEM}.
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
        ((Activity)context).runOnUiThread(() -> {
            alertdialog.show();
            utils.animateView(dialogView);
        });
    }

    /**
     * Show the SDialog for a duration of time.
     * @param duration The duration in milliseconds.
     */
    public void show(long duration) {
        ((Activity) context).runOnUiThread(() -> {
            alertdialog.show();
            utils.animateView(dialogView);
            new CountDownTimer(duration, 10) {
                @Override public void onTick(long duration) {}
                @Override public void onFinish() {dismiss();}
            }.start();
        });
    }

    /**
     * Dismiss the current displayed SDialog.
     */
    public void dismiss() {
        ((Activity)context).runOnUiThread(() -> alertdialog.dismiss());
    }

    /**
     * Dismiss the current displayed SDialog.
     * @param duration The duration in milliseconds.
     */
    public void dismiss(long duration) {
        ((Activity)context).runOnUiThread(() -> new CountDownTimer(duration, 1) {
            @Override public void onTick(long dur) {}
            @Override public void onFinish() {dismiss();}
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
            case THEME_BY_SYSTEM -> {
                if (utils.nightModeON()) darkThemeColors();
                else lightThemeColors();
            }
            case THEME_DARK -> darkThemeColors();
            case THEME_LIGHT -> lightThemeColors();
        }
    }

    protected static class DialogBinding {

        LinearLayout main, holder;
        ImageView icon, like, dislike;
        TextView title, text, positive, negative, neutral, percent;
        SRecyclerView recycler;
        TextInputLayout input;
        TextInputEditText inputed;
        ProgressBar loading, progress;
        PatternLockView pattern;
        Slider seek;

        public DialogBinding(@NonNull View view) {
            main = view.findViewById(R.id.main);
            holder = view.findViewById(R.id.holder);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            text = view.findViewById(R.id.text);
            positive = view.findViewById(R.id.positive);
            negative = view.findViewById(R.id.negative);
            neutral = view.findViewById(R.id.neutral);
            percent = view.findViewById(R.id.percent);
            recycler = view.findViewById(R.id.recycler);
            input = view.findViewById(R.id.input);
            inputed = view.findViewById(R.id.inputed);
            loading = view.findViewById(R.id.loading);
            pattern = view.findViewById(R.id.pattern);
            progress = view.findViewById(R.id.progress);
            seek = view.findViewById(R.id.seek);
            like = view.findViewById(R.id.like);
            dislike = view.findViewById(R.id.dislike);
        }
    }
}
