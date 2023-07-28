package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import kotlin.Suppress;
import smith.lib.alerts.dialog.callbacks.OnClickCallBack;

/**
 * The beautiful and coolest class of SDialog lib.
 * this class create an alert SDialogs that alerts the user what going on.
 */
@Suppress(names = {"Unused"})
public class AlertSDialog extends SDialog {

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public AlertSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
        init();
    }

    /**
     * Declare displayed positive button text and its functionality.
     *
     * @param text     String represent the text of positive button (e.g. "OK")
     * @param callback Click callback using {@link OnClickCallBack}
     */
    public void setPositiveButton(String text, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.positive.setVisibility(View.VISIBLE);
        b.positive.setText(text);
        b.positive.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    /**
     * Declare displayed negative button text and its functionality.
     *
     * @param text     String represent the text of negative button (e.g. "CANCEL")
     * @param callback Click callback using {@link OnClickCallBack}
     */
    public void setNegativeButton(String text, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.negative.setVisibility(View.VISIBLE);
        b.negative.setText(text);
        b.negative.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    /**
     * Declare displayed neutral button text and its functionality.
     *
     * @param text     String represent the text of neutral button (e.g. "HIDE")
     * @param callback Click callback using {@link OnClickCallBack}
     */
    public void setNeutralButton(String text, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.neutral.setVisibility(View.VISIBLE);
        b.neutral.setText(text);
        b.neutral.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
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
     * Set SDialog theme as light, dark or by system.
     *
     * @param theme Options are {@link SDialog#THEME_DARK},
     *              {@link SDialog#THEME_LIGHT} and {@link SDialog#THEME_BY_SYSTEM}.
     */
    public void setTheme(int theme) {
        this.theme = theme;
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
        super.show(duration);
    }

    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.positive.setTextColor(accentColor);
        b.negative.setTextColor(accentColor);
        b.neutral.setTextColor(accentColor);
    }
}
