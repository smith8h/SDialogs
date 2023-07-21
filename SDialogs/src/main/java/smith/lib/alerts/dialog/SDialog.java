    /*
     *
     *
     *    THIS LIBRARY CREATED BY HUSSEIN SHAKIR (SMITH)
     *
     *	TELEGRAM : @SMITHDEV
     *	YOUTUBE : HUSSEIN SMITH (@SMITH8H)
     *
     *	YOU GUYS ARE NOT ALLOWED TO MODIFY THIS LIBRARY,
     *	WITHOT ANY PERMISSION FROM ME PERSONALLY..
     *	ALL RIGHTS RESERVED © HUSSEIN SHAKIR, Dec 2022.
     *
     *
     */

    package smith.lib.alerts.dialog;

    import android.animation.ObjectAnimator;
    import android.content.Context;
    import android.content.res.ColorStateList;
    import android.content.res.Configuration;
    import android.graphics.Color;
    import android.graphics.drawable.*;
    import android.os.Build;
    import android.os.CountDownTimer;
    import android.view.*;
    import android.widget.*;

    import com.google.android.material.slider.Slider;
    import com.google.android.material.textfield.*;

    import smith.lib.views.recyclerview.SRecyclerView;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AlertDialog;

    import com.andrognito.patternlockview.PatternLockView;

    import smith.lib.alerts.dialog.callbacks.OnDismissCallBack;

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

        protected Context context;
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

        protected void init() {
            alertdialog = new AlertDialog.Builder(context).create();
            alertdialog.setView(dialogView);
            alertdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            b = new DialogBinding(dialogView);
        }

        public void setCancelable(boolean cancelable) {
            alertdialog.setCancelable(cancelable);
        }

        public void setOnDismissCallBack(OnDismissCallBack callback) {
            alertdialog.setOnDismissListener(dialogInterface -> callback.onDismiss());
        }

        public void show() {
            alertdialog.show();
            animateView(dialogView);

        }

        public void show(long dur) {
            alertdialog.show();
            animateView(dialogView);
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
            iconColor = darkerColor(accentColor, .2f);
            iconBackground = lighterColor(accentColor, .4f);
            titleColor = darkerColor(accentColor, .2f);
            textColor = darkerColor(accentColor, .35f);
            backgroundColor = lighterColor(accentColor, .9f);
            hintColor = darkerColor(accentColor, .6f);
            accentColor = darkerColor(accentColor, .9f);
        }

        protected void darkThemeColors() {
            iconColor = lighterColor(accentColor, .8f);
            iconBackground = darkerColor(accentColor, .4f);
            titleColor = lighterColor(accentColor, .8f);
            textColor = lighterColor(accentColor, .65f);
            backgroundColor = darkerColor(accentColor, .1f);
            hintColor = lighterColor(accentColor, .4f);
            accentColor = lighterColor(accentColor, .1f);
        }

        protected void animateView(@NonNull View v) {
            v.setAlpha(0f);
            ObjectAnimator anim = new ObjectAnimator();
            anim.setDuration(620);
            anim.setFloatValues(0f, 1f);
            anim.setPropertyName("alpha");
            anim.setTarget(v);
            anim.start();
        }

        protected boolean nightModeON() {
            int flags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            return flags == Configuration.UI_MODE_NIGHT_YES;
        }

        protected void setBackgroundColor(@NonNull View view, int color) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(color);
            gradientDrawable.setCornerRadius(dp(24));

            RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(accentColor),
                    gradientDrawable, null);

            view.setBackground(rippleDrawable);
        }

        protected int darkerColor(int color, float factor) {
            int alpha = Color.alpha(color);
            int red = Color.red(color);
            int green = Color.green(color);
            int blue = Color.blue(color);
            return Color.argb(alpha,
                    Math.max((int) (red * factor), 0),
                    Math.max((int) (green * factor), 0),
                    Math.max((int) (blue * factor), 0));
        }

        protected int lighterColor(int color, float factor) {
            int alpha = Color.alpha(color);
            int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
            int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
            int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
            return Color.argb(alpha, red, green, blue);
        }

        protected int oppositeColor(int color) {
            float[] hsv = new float[3];
            Color.RGBToHSV(Color.red(color),
                    Color.green(color),
                    Color.blue(color), hsv);
            hsv[0] = (hsv[0] + 180) % 360;
            return Color.HSVToColor(hsv);
        }

        protected int dp(int dp) {
            float density = context.getResources().getDisplayMetrics().density;
            return Math.round(dp * density);
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
