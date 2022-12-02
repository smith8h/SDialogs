package smith.lib.alerts.dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

public class SDialog {
    
    private Context context;
    private View dialogView;
    private AlertDialog alertdialog;
    
    
    
    private void init() {
        this.alertdialog = new AlertDialog.Builder(context).create();
        this.alertdialog.setView(this.dialogView);
        this.alertdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.alertdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    
    public void setCancelable(boolean cancelable) {
        this.alertdialog.setCancelable(cancelable);
    }
    
    public void show() {
        animateView(this.dialogView);
        this.alertdialog.show();
    }
    
    public void dismiss() {
        this.alertdialog.dismiss();
    }
    
    
    
    public static class AlertSDialog {
        
        Context context;
        SDialog dialog;
        View sdialogView;
        int titleColor, textColor, buttonColor = 0xFFA7B4C5, backgroundColor;
        boolean isPositive = false;
        boolean isNegative = false;
        boolean isNeutral = false;
        
        public AlertSDialog(Context context) {
            this.context = context;
            this.dialog = new SDialog();
            this.sdialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
        }
        
        public AlertSDialog setTitle(String title) {
            ((TextView) this.sdialogView.findViewById(R.id.title)).setText(title);
            return this;
        }
        
        public AlertSDialog setText(String text) {
            ((TextView) this.sdialogView.findViewById(R.id.text)).setText(text);
            return this;
        }
        
        public AlertSDialog setPositiveButton(String positive, View.OnClickListener listener) {
            ((LinearLayout) this.sdialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
            ((TextView) this.sdialogView.findViewById(R.id.positive)).setText(positive);
            ((TextView) this.sdialogView.findViewById(R.id.positive)).setOnClickListener(listener);
            return this;
        }
        
        public AlertSDialog setNegativeButton(String negative, View.OnClickListener listener) {
            ((LinearLayout) this.sdialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
            ((TextView) this.sdialogView.findViewById(R.id.negative)).setVisibility(View.VISIBLE);
            ((TextView) this.sdialogView.findViewById(R.id.negative)).setText(negative);
            ((TextView) this.sdialogView.findViewById(R.id.negative)).setOnClickListener(listener);
            return this;
        }
        
        public AlertSDialog setNeutralButton(String neutral, View.OnClickListener listener) {
            ((LinearLayout) this.sdialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
            ((TextView) this.sdialogView.findViewById(R.id.neutral)).setVisibility(View.VISIBLE);
            ((TextView) this.sdialogView.findViewById(R.id.neutral)).setText(neutral);
            ((TextView) this.sdialogView.findViewById(R.id.neutral)).setOnClickListener(listener);
            return this;
        }
        
        public AlertSDialog setButtonsColor(int color) {
            this.buttonColor = color;
            return this;
        }
    
        public AlertSDialog setButtonsColor(String color) {
            this.buttonColor = Color.parseColor(color);
            return this;
        }

        public SDialog create() {
            this.dialog.context = this.context;
            this.dialog.dialogView = this.sdialogView;
            this.dialog.init();
            
            if (dialog.nightModeON()) {
                this.titleColor = this.dialog.lighterColor(buttonColor, .5f);
                this.textColor = this.dialog.lighterColor(buttonColor, .4f);
                this.backgroundColor = this.dialog.darkerColor(buttonColor, .16f);
                this.buttonColor = this.dialog.lighterColor(buttonColor, .1f);
            } else {
                this.titleColor = this.dialog.darkerColor(buttonColor, .5f);
                this.textColor = this.dialog.darkerColor(buttonColor, .4f);
                this.backgroundColor = this.dialog.lighterColor(buttonColor, .16f);
                this.buttonColor = this.dialog.darkerColor(buttonColor, .1f);
            }

            this.dialog.setBackgroundColor(this.sdialogView.findViewById(R.id.main), backgroundColor);
            ((TextView) this.sdialogView.findViewById(R.id.title)).setTextColor(titleColor);
            ((TextView) this.sdialogView.findViewById(R.id.text)).setTextColor(textColor);
            ((TextView) this.sdialogView.findViewById(R.id.positive)).setTextColor(buttonColor);
            ((TextView) this.sdialogView.findViewById(R.id.negative)).setTextColor(buttonColor);
            ((TextView) this.sdialogView.findViewById(R.id.neutral)).setTextColor(buttonColor);

            return dialog;
        }
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
