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

public class FeedbackSDialog extends SDialog {

    private OnFeedbackSubmitCallBack callBack = isLiked -> {
        dismiss();
    };

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

    public void setIconResource(@DrawableRes int icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageResource(icon);
    }

    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageDrawable(icon);
    }

    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageBitmap(icon);
    }

    public void setTitle(String title) {
        b.title.setText(title);
    }

    public void setText(String text) {
        b.text.setText(text);
    }

    public void setText(@StringRes int text) {
        b.text.setText(text);
    }

    public void setOnFeedbackSubmitCallBack(OnFeedbackSubmitCallBack callBack) {
        this.callBack = callBack;
    }

    public void setAccentColor(int color) {
        accentColor = color;
    }

    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    public void setTheme(int theme) {
        super.theme = theme;
    }

    public int getAccentColor() {
        return accentColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
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
    public void show(long dur) {
        update();
        super.show(dur);
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
