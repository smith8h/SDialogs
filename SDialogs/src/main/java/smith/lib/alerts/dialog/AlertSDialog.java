package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
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
        dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
        init();
    }

    /**
     * Declare displayed positive button text and its functionality.
     * @param text String represent the text of positive button (e.g. "OK")
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
     * @param text String represent the text of negative button (e.g. "CANCEL")
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
     * @param text String represent the text of neutral button (e.g. "HIDE")
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
