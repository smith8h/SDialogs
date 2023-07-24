package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import kotlin.Suppress;
import smith.lib.alerts.dialog.callbacks.OnClickCallBack;

@Suppress(names = {"Unused"})
public class AlertSDialog extends SDialog {

    @SuppressLint("InflateParams")
    public AlertSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
        init();
    }

    public void setPositiveButton(String positive, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.positive.setVisibility(View.VISIBLE);
        b.positive.setText(positive);
        b.positive.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setNegativeButton(String negative, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.negative.setVisibility(View.VISIBLE);
        b.negative.setText(negative);
        b.negative.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setNeutralButton(String neutral, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.neutral.setVisibility(View.VISIBLE);
        b.neutral.setText(neutral);
        b.neutral.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }
    
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
