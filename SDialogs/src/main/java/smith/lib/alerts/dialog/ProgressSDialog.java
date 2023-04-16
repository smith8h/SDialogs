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

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;

public class ProgressSDialog extends SDialog {
    
    OnProgressCallBack callback;
    private final int MAX = 100;
    private final int MIN = 0;
    
    public ProgressSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_progress, null);
        init();
        
        ((ProgressBar) dialogView.findViewById(R.id.progress)).setMin(MIN);
        ((ProgressBar) dialogView.findViewById(R.id.progress)).setMax(MAX);
    }
    
    public void setIconResource(int icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageResource(icon);
    }
    
    public void setIconDrawable(Drawable icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageDrawable(icon);
    }
    
    public void setIconBitmap(Bitmap icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageBitmap(icon);
    }

    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
    }

    public void setText(String text) {
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }
    
    public void setText(int text) {
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }
    
    public void setMin(int min) {
        ((ProgressBar) dialogView.findViewById(R.id.progress)).setMin(min);
    }
    
    public void setMax(int max) {
        ((ProgressBar) dialogView.findViewById(R.id.progress)).setMax(max);
    }
    
    public void setProgress(int progress) {
        ((ProgressBar) dialogView.findViewById(R.id.progress)).setProgress(progress);
        
        setProgressText(getProgress());
        
        if (getProgress() == getMax()) {
            dismiss();
            if (callback != null) callback.onFinish();
        }
    }
    
    public void setAccentColor(int color) {
        accentColor = color;
    }

    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
    
    private void setProgressText(int progress) {
        int percent = (getProgress() * 100) / getMax();
        String info = getProgress() + "/" + getMax() + " (" + percent + "%)";
        ((TextView) dialogView.findViewById(R.id.percent)).setText(info);
        if (callback != null) callback.onProgress(getProgress(), percent);
    }
    
    public void setOnProgressCallBack(OnProgressCallBack callback) {
        this.callback = callback;
    }
    
    public int getProgress() {
        return ((ProgressBar) dialogView.findViewById(R.id.progress)).getProgress();
    }
    
    public int getMin() {
        return ((ProgressBar) dialogView.findViewById(R.id.progress)).getMin();
    }
    
    public int getMax() {
        return ((ProgressBar) dialogView.findViewById(R.id.progress)).getMax();
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
        setCancelable(false);
        update();
        super.show();
    }
    
    @Override
    public void show(long dur) {
        setCancelable(false);
        update();
        super.show();
    }
        
    private void update() {
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();

        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        ((TextView) dialogView.findViewById(R.id.text)).setTextColor(textColor);
        ((TextView) dialogView.findViewById(R.id.percent)).setTextColor(textColor);
        ((ProgressBar) dialogView.findViewById(R.id.progress)).setProgressTintList(ColorStateList.valueOf(accentColor));
    }
}
