package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.*;
import java.util.List;
import smith.lib.alerts.dialog.callbacks.OnDrawPatternCallBack;

public class PatternSDialog extends SDialog {

    private DialogViews views;
    
    public PatternSDialog(Context context) {
    	super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_pattern, null);
        init();
        views = new DialogViews(dialogView);
        views.pattern.setCorrectStateColor(0xFF00D927);
        views.pattern.setWrongStateColor(0xFFFF6060);
    }
    
    public void setIconResource(int icon) {
        views.icon.setVisibility(View.VISIBLE);
    	views.icon.setImageResource(icon);
    }
    
    public void setIconDrawable(Drawable icon) {
        views.icon.setVisibility(View.VISIBLE);
    	views.icon.setImageDrawable(icon);
    }
    
    public void setIconBitmap(Bitmap icon) {
        views.icon.setVisibility(View.VISIBLE);
    	views.icon.setImageBitmap(icon);
    }

    public void setTitle(String title) {
        views.title.setText(title);
    }
    
    public void setOnDrawPatternCallBack(OnDrawPatternCallBack callback) {
    	views.pattern.addPatternLockListener(new PatternLockViewListener() {
            @Override public void onCleared() { callback.onClearDrawing(); }
            @Override public void onProgress(List<PatternLockView.Dot> dots) {}
            @Override public void onComplete(List<PatternLockView.Dot> dots) {
                callback.onCompleteDrawing(PatternLockUtils.patternToString(views.pattern, dots));
            }
            @Override public void onStarted() { callback.onStartDrawing(); }
        });
    }
    
    public void setPatternMode(int patternMode) {
    	views.pattern.setViewMode(patternMode);
    }
    
    public void setPatternAnimationDuration(int milliseconds) {
        views.pattern.setDotAnimationDuration(milliseconds);
    	views.pattern.setPathEndAnimationDuration(milliseconds);
    }
    
    public void setPatternDotCount(int count) {
    	views.pattern.setDotCount(count);
    }
    
    public void setPatternDotNormalSizeDp(int size) {
    	views.pattern.setDotNormalSize((int) ResourceUtils.getDimensionInPx(context, size));
    }
    
    public void setPatternDotSelectedSizeDp(int size) {
    	views.pattern.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(context, size));
    }
    
    public void setPatternPathWidthDp(int width) {
    	views.pattern.setPathWidth((int) ResourceUtils.getDimensionInPx(context, width));
    }
    
    public void setPatternAspectRatioEnabled(boolean enabled) {
    	views.pattern.setAspectRatioEnabled(enabled);
    }
    
    public void setPatternAspectRatio(int aspectRatio) {
    	views.pattern.setAspectRatio(aspectRatio);
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
        
        setBackgroundColor(views.main, backgroundColor);
        views.icon.setColorFilter(iconColor);
        views.title.setTextColor(titleColor);
        views.pattern.setNormalStateColor(textColor);
    }
    
    private class DialogViews {
        
        LinearLayout main;
        ImageView icon;
        TextView title;
        PatternLockView pattern;
        
        private DialogViews(View view) {
            main = view.findViewById(R.id.main);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            pattern = view.findViewById(R.id.pattern);
        }
    }
}