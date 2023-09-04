package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import smith.lib.alerts.dialog.callbacks.OnBindCustomViewCallBack;

/**
 * The fully customizable class of SDialog lib.
 * this class create an alert SDialogs that is full depends on you as you set its layout and design and functionality.
 */
@SuppressWarnings({"unused"})
public class CustomSDialog extends SDialog {

    private final View view;

    /**
     * Create new instance of fully customizable dialog.
     * @param context Current context (or Activity).
     * @param view the custom view as  {@link View}.
     */
    public CustomSDialog(Context context, View view) {
        this.context = context;
        this.view = view;
        dialogView = this.view;
        init();
    }

    /**
     * Create new instance of fully customizable dialog.
     * @param context Current context (or Activity).
     * @param layout the custom layout resource as  {@link LayoutRes} res.
     */
    public CustomSDialog(Context context, @LayoutRes int layout) {
        this.context = context;
        this.view = ((Activity) context).getLayoutInflater().inflate(layout, null);
        dialogView = this.view;
        init();
    }

    /**
     * Set the callback to interact with the dialog onBindingView of the SDialog.
     * @param callback On bind view callback using {@link OnBindCustomViewCallBack}
     */
    public void setOnBindViewCallBack(@NonNull OnBindCustomViewCallBack callback) {
        callback.onBindCustomView(view);
    }

    /**
     * This setter won't work on custom type of SDialog.
     */
    @Override
    public void setAccentColor(int color) {
    }

    /**
     * This setter won't work on custom type of SDialog.
     */
    @Override
    public void setAccentColor(String color) {
    }

    /**
     * This setter won't work on custom type of SDialog.
     */
    @Override
    public void setTheme(int theme) {
    }
}
