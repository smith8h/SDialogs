package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import smith.lib.alerts.dialog.callbacks.OnBindCustomViewCallBack;

public class CustomSDialog extends SDialog {

    public CustomSDialog(Context context) {
        this.context = context;
    }

    public void setView(View view, @NonNull OnBindCustomViewCallBack callback) {
        dialogView = view;
        callback.onBindCustomView(view);
        init();
    }

    public void setView(int layout, @NonNull OnBindCustomViewCallBack callback) {
        View view = ((Activity) context).getLayoutInflater().inflate(layout, null);
        dialogView = view;
        callback.onBindCustomView(view);
        init();
    }

    @Override
    public void setTitle(String title) {}

    @Override
    public void setTitle(int resTitle) {}
}
