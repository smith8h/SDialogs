package smith.lib.alerts.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.*;
import android.os.CountDownTimer;
import android.view.*;
import android.widget.*;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.*;
import smith.lib.alerts.dialog.callbacks.OnDismissCallBack;
import smith.lib.alerts.dialog.utils.SDialogUtils;
import smith.lib.views.recyclerview.SRecyclerView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import co.encept.patternlockview.PatternLockView;

public class SDialog {

    public static final int COLOR_DEFAULT = 0xFFA7B4C5;

    public static final int THEME_BY_SYSTEM = 0;
    public static final int THEME_DARK = 1;
    public static final int THEME_LIGHT = 2;

    public static final String KEY_ITEM_ID = "id";
    public static final String KEY_ITEM_TEXT = "text";
    public static final String KEY_ITEM_CHECKED = "checked";

    public static final int PATTERN_MODE_CORRECT = PatternLockView.PatternViewMode.CORRECT;
    public static final int PATTERN_MODE_WRONG = PatternLockView.PatternViewMode.WRONG;

    protected View dialogView;
    protected AlertDialog alertdialog;
    protected DialogBinding b;

    protected int iconColor;
    protected int titleColor;
    protected int textColor;
    protected int iconBackground;
    protected int accentColor = COLOR_DEFAULT;
    protected int backgroundColor;
    protected int theme = THEME_BY_SYSTEM;
    protected int hintColor;
    protected SDialogUtils utils;

    protected SDialog(Context context) {
        alertdialog = new AlertDialog.Builder(context).create();
        alertdialog.setView(dialogView);
        alertdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        b = new DialogBinding(dialogView);
        utils = new SDialogUtils(context);
    }

    public void setCancelable(boolean cancelable) {
        alertdialog.setCancelable(cancelable);
    }

    public void setOnDismissCallBack(OnDismissCallBack callback) {
        alertdialog.setOnDismissListener(dialogInterface -> callback.onDismiss());
    }

    public void show() {
        alertdialog.show();
        utils.animateView(dialogView);
    }

    public void show(long dur) {
        alertdialog.show();
        utils.animateView(dialogView);
        new CountDownTimer(dur, 1) {
            @Override
            public void onTick(long duration) {
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        }.start();
    }

    public void dismiss() {
        alertdialog.dismiss();
    }

    protected void lightThemeColors() {
        iconColor = utils.darkerColor(accentColor, .2f);
        iconBackground = utils.lighterColor(accentColor, .6f);
        titleColor = utils.darkerColor(accentColor, .2f);
        textColor = utils.darkerColor(accentColor, .35f);
        backgroundColor = utils.lighterColor(accentColor, .9f);
        hintColor = utils.darkerColor(accentColor, .6f);
        accentColor = utils.darkerColor(accentColor, .9f);
    }

    protected void darkThemeColors() {
        iconColor = utils.lighterColor(accentColor, .8f);
        iconBackground = utils.darkerColor(accentColor, .4f);
        titleColor = utils.lighterColor(accentColor, .8f);
        textColor = utils.lighterColor(accentColor, .65f);
        backgroundColor = utils.darkerColor(accentColor, .1f);
        hintColor = utils.lighterColor(accentColor, .4f);
        accentColor = utils.lighterColor(accentColor, .1f);
    }

    protected static class DialogBinding {

        LinearLayout main, holder;
        ImageView icon, like, dislike;
        TextView title, text, positive, negative, neutral, percent;
        SRecyclerView recycler;
        TextInputLayout input;
        TextInputEditText inputed;
        ProgressBar loading, progress;
        PatternLockView pattern;
        Slider seek;

        public DialogBinding(@NonNull View view) {
            main = view.findViewById(R.id.main);
            holder = view.findViewById(R.id.holder);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            text = view.findViewById(R.id.text);
            positive = view.findViewById(R.id.positive);
            negative = view.findViewById(R.id.negative);
            neutral = view.findViewById(R.id.neutral);
            percent = view.findViewById(R.id.percent);
            recycler = view.findViewById(R.id.recycler);
            input = view.findViewById(R.id.input);
            inputed = view.findViewById(R.id.inputed);
            loading = view.findViewById(R.id.loading);
            pattern = view.findViewById(R.id.pattern);
            progress = view.findViewById(R.id.progress);
            seek = view.findViewById(R.id.seek);
            like = view.findViewById(R.id.like);
            dislike = view.findViewById(R.id.dislike);
        }
    }
}
