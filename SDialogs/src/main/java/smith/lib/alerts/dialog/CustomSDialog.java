package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import smith.lib.alerts.dialog.callbacks.OnBindCustomViewCallBack;

/**
 * The fully customizable class of SDialog lib.
 * this class create an alert SDialogs that is full depends on you as you set its layout and design and functionality.
 */
@SuppressWarnings({"unused"})
public class CustomSDialog extends SDialog {

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    public CustomSDialog(Context context) {
        this.context = context;
    }


    /**
     * Set the main view of the SDialog.
     * @param view As {@link View}.
     * @param callback On bind view callback using {@link OnBindCustomViewCallBack}
     */
    public void setView(View view, @NonNull OnBindCustomViewCallBack callback) {
        dialogView = view;
        init();
        callback.onBindCustomView(view);
    }

    /**
     * Set the main view of the SDialog.
     * @param layout As direct resource layout id.
     * @param callback On bind view callback using {@link OnBindCustomViewCallBack}
     */
    public void setView(int layout, @NonNull OnBindCustomViewCallBack callback) {
        View view = ((Activity) context).getLayoutInflater().inflate(layout, null);
        dialogView = view;
        init();
        callback.onBindCustomView(view);
    }

    /**
     * This setter wont work on custom type of SDialog.
     */
    @Override
    public void setAccentColor(int color) {
    }

    /**
     * This setter wont work on custom type of SDialog.
     */
    @Override
    public void setAccentColor(String color) {
    }

    /**
     * This setter wont work on custom type of SDialog.
     */
    @Override
    public void setTheme(int theme) {
    }
}
