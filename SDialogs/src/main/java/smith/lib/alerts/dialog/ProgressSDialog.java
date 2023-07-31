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

import smith.lib.alerts.dialog.callbacks.OnClickCallBack;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;

/**
 * The beautiful and smoothest class of SDialog lib.
 * this class create a progress loading SDialogs that inform the user about the progress of the task going on background.
 */
@SuppressWarnings({"unused"})
public class ProgressSDialog extends SDialog {
    
    private OnProgressCallBack callback = null;

    /**
     * Pass the current context you using this sdialog from.
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
     * @param callback a callback using {@link OnProgressCallBack}.
     */
    public void setOnProgressCallBack(OnProgressCallBack callback) {
        this.callback = callback;
    }

    /**
     * Set the negative button text and a functional interface to it.
     * @param text a string text to display on the negative button.
     * @param clickCallBack a callback using {@link OnClickCallBack}.
     */
    public void setNegativeButtonAction(String text, OnClickCallBack clickCallBack) {
        b.negative.setVisibility(View.VISIBLE);
        b.negative.setText(text);
        b.negative.setOnClickListener(v -> {
            clickCallBack.onClick();
            this.callback = null;
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
        utils.backgroundColor(b.negative, iconBackground);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.percent.setTextColor(textColor);
        b.negative.setTextColor(iconColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            b.progress.setProgressDrawable(utils.createProgressLayerList(accentColor, iconBackground));
        else
            b.progress.setProgressTintList(ColorStateList.valueOf(accentColor));

    }
}
