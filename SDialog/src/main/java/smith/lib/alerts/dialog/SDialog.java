package smith.lib.alerts.dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

public class SDialog {
    
    private Context context;
    private View dialogView;
    private AlertDialog alertdialog;
    public static final int SYSTEM_THEME = 0;
    public static final int DARK_THEME = 1;
    public static final int LIGHT_THEME = 2;
    
    
    
    private void init() {
        alertdialog = new AlertDialog.Builder(context).create();
        alertdialog.setView(dialogView);
        alertdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    
    public void setCancelable(boolean cancelable) {
        alertdialog.setCancelable(cancelable);
    }
    
    public void setOnDismissCallBack(OnDismissCallBack callback) {
        alertdialog.setOnDismissListener(dialogInterface -> callback.onDismiss());
    }
    
    public void show() {
        animateView(dialogView);
        alertdialog.show();
    }
    
    public void show(long dur) {
        animateView(dialogView);
        alertdialog.show();
        new CountDownTimer(dur, 1) {
            @Override public void onTick(long duration) {}
            @Override public void onFinish() { dismiss(); }
        }.start();
    }
    
    public void dismiss() {
        alertdialog.dismiss();
    }
    
    
    
    public static class CustomSDialog {
        
        Context context;
        SDialog dialog;
        
        public CustomSDialog(Context context) {
            this.context = context;
            dialog = new SDialog();
            dialog.context = context;
        }
        
        public CustomSDialog setView(View view, OnBindCustomViewCallBack callback) {
            dialog.dialogView = view;
            callback.onBindCustomView(view);
            return this;
        }
        
        public CustomSDialog setView(int layout, OnBindCustomViewCallBack callback) {
            View view = ((Activity)context).getLayoutInflater().inflate(layout, null);
            dialog.dialogView = view;
            callback.onBindCustomView(view);
            return this;
        }
        
        public SDialog create() {
            return dialog;
        }
    }
    
    
    
    public static class LoadingSDialog {
        
        Context context;
        SDialog dialog;
        View sdialogView;
        int titleColor, textColor, loadingColor = 0xFFA7B4C5, backgroundColor;
        int theme = SYSTEM_THEME;
        
        public LoadingSDialog(Context context) {
            this.context = context;
            dialog = new SDialog();
            sdialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.sdialog_loading, null);
            dialog.context = context;
            dialog.dialogView = sdialogView;
            dialog.init();
        }
        
        public LoadingSDialog setTitle(String title) {
            ((TextView) sdialogView.findViewById(R.id.title)).setText(title);
            return this;
        }
        
        public LoadingSDialog setText(String text) {
            ((TextView) sdialogView.findViewById(R.id.text)).setText(text);
            return this;
        }
        
        public LoadingSDialog setLoadingColor(int color) {
            loadingColor = color;
            return this;
        }
    
        public LoadingSDialog setLoadingColor(String color) {
            loadingColor = Color.parseColor(color);
            return this;
        }
        
        public LoadingSDialog setSDialogTheme(int theme) {
            this.theme = theme;
            return this;
        }
        
        public SDialog create() {
            dialog.setCancelable(false);
            
            if (theme == SYSTEM_THEME) {
                if (dialog.nightModeON()) darkThemeColors();
                else lightThemeColors();
            } else if (theme == DARK_THEME) darkThemeColors();
            else if (theme == LIGHT_THEME) lightThemeColors();
            
            dialog.setBackgroundColor(sdialogView.findViewById(R.id.main), backgroundColor);
            ((TextView) sdialogView.findViewById(R.id.title)).setTextColor(titleColor);
            ((TextView) sdialogView.findViewById(R.id.text)).setTextColor(textColor);
            ((ProgressBar) sdialogView.findViewById(R.id.loading)).setIndeterminateTintList(ColorStateList.valueOf(loadingColor));
            
            return dialog;
        }
        
        private void lightThemeColors() {
            titleColor = dialog.darkerColor(loadingColor, .2f);
            textColor = dialog.darkerColor(loadingColor, .35f);
            backgroundColor = dialog.lighterColor(loadingColor, .88f);
            loadingColor = dialog.darkerColor(loadingColor, .9f);
        }

        private void darkThemeColors() {
            titleColor = dialog.lighterColor(loadingColor, .8f);
            textColor = dialog.lighterColor(loadingColor, .65f);
            backgroundColor = dialog.darkerColor(loadingColor, .12f);
            loadingColor = dialog.lighterColor(loadingColor, .1f);
        }
    }
    
    
    
    public static class AlertSDialog {
        
        Context context;
        SDialog dialog;
        View sdialogView;
        int titleColor, textColor, buttonColor = 0xFFA7B4C5, backgroundColor;
        int theme = SYSTEM_THEME;
        
        public AlertSDialog(Context context) {
            this.context = context;
            dialog = new SDialog();
            sdialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
            dialog.context = context;
            dialog.dialogView = sdialogView;
            dialog.init();
        }
        
        public AlertSDialog setTitle(String title) {
            ((TextView) sdialogView.findViewById(R.id.title)).setText(title);
            return this;
        }
        
        public AlertSDialog setText(String text) {
            ((TextView) sdialogView.findViewById(R.id.text)).setText(text);
            return this;
        }
        
        public AlertSDialog setPositiveButton(String positive, OnClickCallBack callback) {
            ((LinearLayout) sdialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
            ((TextView) sdialogView.findViewById(R.id.positive)).setVisibility(View.VISIBLE);
            ((TextView) sdialogView.findViewById(R.id.positive)).setText(positive);
            ((TextView) sdialogView.findViewById(R.id.positive)).setOnClickListener(v -> {
                callback.onClick();
                dialog.dismiss();
            });
            return this;
        }
        
        public AlertSDialog setNegativeButton(String negative, OnClickCallBack callback) {
            ((LinearLayout) sdialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
            ((TextView) sdialogView.findViewById(R.id.negative)).setVisibility(View.VISIBLE);
            ((TextView) sdialogView.findViewById(R.id.negative)).setText(negative);
            ((TextView) sdialogView.findViewById(R.id.negative)).setOnClickListener(v -> {
                callback.onClick();
                dialog.dismiss();
            });
            return this;
        }
        
        public AlertSDialog setNeutralButton(String neutral, OnClickCallBack callback) {
            ((LinearLayout) sdialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
            ((TextView) sdialogView.findViewById(R.id.neutral)).setVisibility(View.VISIBLE);
            ((TextView) sdialogView.findViewById(R.id.neutral)).setText(neutral);
            ((TextView) sdialogView.findViewById(R.id.neutral)).setOnClickListener(v -> {
                callback.onClick();
                dialog.dismiss();
            });
            return this;
        }
        
        public AlertSDialog setButtonsColor(int color) {
            buttonColor = color;
            return this;
        }
    
        public AlertSDialog setButtonsColor(String color) {
            buttonColor = Color.parseColor(color);
            return this;
        }
        
        public AlertSDialog setSDialogTheme(int theme) {
            this.theme = theme;
            return this;
        }

        public SDialog create() {
            if (theme == SYSTEM_THEME) {
                if (dialog.nightModeON()) darkThemeColors();
                else lightThemeColors();
            } else if (theme == DARK_THEME) darkThemeColors();
            else if (theme == LIGHT_THEME) lightThemeColors();
            
            dialog.setBackgroundColor(sdialogView.findViewById(R.id.main), backgroundColor);
            ((TextView) sdialogView.findViewById(R.id.title)).setTextColor(titleColor);
            ((TextView) sdialogView.findViewById(R.id.text)).setTextColor(textColor);
            ((TextView) sdialogView.findViewById(R.id.positive)).setTextColor(buttonColor);
            ((TextView) sdialogView.findViewById(R.id.negative)).setTextColor(buttonColor);
            ((TextView) sdialogView.findViewById(R.id.neutral)).setTextColor(buttonColor);

            return dialog;
        }
        
        private void lightThemeColors() {
            titleColor = dialog.darkerColor(buttonColor, .2f);
            textColor = dialog.darkerColor(buttonColor, .35f);
            backgroundColor = dialog.lighterColor(buttonColor, .88f);
            buttonColor = dialog.darkerColor(buttonColor, .9f);
        }

        private void darkThemeColors() {
            titleColor = dialog.lighterColor(buttonColor, .8f);
            textColor = dialog.lighterColor(buttonColor, .65f);
            backgroundColor = dialog.darkerColor(buttonColor, .12f);
            buttonColor = dialog.lighterColor(buttonColor, .1f);
        }
    }
    
    
    
    public interface OnDismissCallBack {
        public void onDismiss();
    }
    
    
    
    public interface OnClickCallBack {
        public void onClick();
    }
    
    
    
    public interface OnBindCustomViewCallBack {
        public void onBindCustomView(View customView);
    }
    
    
    
    private void animateView(View v) {
        v.setAlpha(0f);
        ObjectAnimator animator = new ObjectAnimator();
        animator.setDuration(310);
        animator.setFloatValues(0f, 1f);
        animator.setPropertyName("alpha");
        animator.setTarget(v);
        animator.start();
    }

    private boolean nightModeON() {
        int flags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightModeOn = flags == Configuration.UI_MODE_NIGHT_YES;
        if (isNightModeOn) return true;
        else return false;
    }

    private void setBackgroundColor(View view, int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(80f);
        view.setBackground(gradientDrawable);
    }

    private int darkerColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha,
                Math.max((int)(red * factor), 0),
                Math.max((int)(green * factor), 0),
                Math.max((int)(blue * factor), 0));
    }

    private int lighterColor(int color, float factor) {
        int alpha = Color.alpha(color);
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(alpha, red, green, blue);
    }

    private int oppositeColor(int color) {
        float[] hsv = new float[3];
        Color.RGBToHSV(Color.red(color),
                Color.green(color),
                Color.blue(color), hsv);
        hsv[0] = (hsv[0] + 180) % 360;
        return Color.HSVToColor(hsv);
    }
    
    

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
}
