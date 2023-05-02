package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.*;
import java.util.List;
import smith.lib.alerts.dialog.callbacks.OnDrawPatternCallBack;

public class PatternSDialog extends SDialog {

    public PatternSDialog(Context context) {
    	super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_pattern, null);
        init();
        
        setCancelable(false);
    }
    
    public void setIconResource(int icon) {
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
    
    public void setOnDrawPatternCallBack(OnDrawPatternCallBack callback) {
    	b.pattern.addPatternLockListener(new PatternLockViewListener() {
            @Override public void onCleared() { callback.onClearDrawing(); }
            @Override public void onProgress(List<PatternLockView.Dot> dots) {}
            @Override public void onComplete(List<PatternLockView.Dot> dots) {
                callback.onCompleteDrawing(PatternLockUtils.patternToString(b.pattern, dots));
            }
            @Override public void onStarted() { callback.onStartDrawing(); }
        });
    }
    
    public void setPatternMode(int patternMode) {
    	b.pattern.setViewMode(patternMode);
    }
    
    public void setPatternAnimationDuration(int milliseconds) {
        b.pattern.setDotAnimationDuration(milliseconds);
    	b.pattern.setPathEndAnimationDuration(milliseconds);
    }
    
    public void setPatternDotCount(int count) {
    	b.pattern.setDotCount(count);
    }
    
    public void setPatternDotNormalSizeDp(int size) {
    	b.pattern.setDotNormalSize(dpToPx(size));
    }
    
    public void setPatternDotSelectedSizeDp(int size) {
    	b.pattern.setDotSelectedSize(dpToPx(size));
    }
    
    public void setPatternPathWidthDp(int width) {
    	b.pattern.setPathWidth(dpToPx(width));
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
    
    public int getPatternNormalStateColor() {
        return textColor;
    }
    
    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(false);
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
    
    public void dismiss(int duration) {
    	new CountDownTimer(duration, 1) {
            @Override public void onTick(long dur) {}
            @Override public void onFinish() { dismiss(); }
        }.start();
    }
    
    private void update() {
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();
        
        setBackgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.pattern.setNormalStateColor(textColor);
    }
}