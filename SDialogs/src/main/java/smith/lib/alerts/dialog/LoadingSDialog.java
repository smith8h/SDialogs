package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.annotation.StringRes;

/**
 * The beautiful and coolest class of SDialog lib.
 * this class create a loading SDialogs that inform the user to wait for some time while doing something in backend.
 */
public class LoadingSDialog extends SDialog {

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public LoadingSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_loading, null);
        init();
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
     * Set text {@link String} to current SDialog.
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
        setCancelable(false);
        update();
        super.show();
    }
    
    @Override
    public void show(long duration) {
        setCancelable(false);
        update();
        super.show(duration);
    }
        
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.loading.setIndeterminateTintList(ColorStateList.valueOf(accentColor));
    }
}
