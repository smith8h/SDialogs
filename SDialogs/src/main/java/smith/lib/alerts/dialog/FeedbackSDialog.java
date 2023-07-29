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
import smith.lib.alerts.dialog.callbacks.OnFeedbackSubmitCallBack;

/**
 * The beautiful and fastest class of SDialog lib.
 * this class create a feedback SDialogs that let the user suggests the most liked or unliked things on your app.
 */
public class FeedbackSDialog extends SDialog {

    private OnFeedbackSubmitCallBack callBack = isLiked -> dismiss();

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public FeedbackSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_feedback, null);
        init();

        b.like.setOnClickListener(v -> {
            callBack.onSubmit(true);
            dismiss();
        });

        b.dislike.setOnClickListener(v -> {
            callBack.onSubmit(false);
            dismiss();
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
     * Add a functionality to submit feedback interface.
     * @param callBack a functional submit callback using {@link OnFeedbackSubmitCallBack}
     */
    public void setOnFeedbackSubmitCallBack(OnFeedbackSubmitCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * Set the accent color of current SDialog to create the theme from it.
     * @param color As Int color.
     */
    public void setAccentColor(int color) {
        accentColor = color;
    }

    /**
     * Set the accent color of current SDialog to create the whole theme colors.
     * @param color As string hex color.
     */
    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    /**
     * Set SDialog theme as light, dark or by system.
     * @param theme Options are {@link SDialog#THEME_DARK},
     * {@link SDialog#THEME_LIGHT} and {@link SDialog#THEME_BY_SYSTEM}.
     */
    public void setTheme(int theme) {
        super.theme = theme;
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
        utils.backgroundColor(b.like, iconBackground);
        utils.backgroundColor(b.dislike, iconBackground);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.like.setColorFilter(iconColor);
        b.dislike.setColorFilter(iconColor);
    }
}
