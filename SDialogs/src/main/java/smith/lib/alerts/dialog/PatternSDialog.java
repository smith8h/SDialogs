package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.List;

import co.encept.patternlockview.PatternLockView;
import co.encept.patternlockview.listener.PatternLockViewListener;
import co.encept.patternlockview.utils.PatternLockUtils;
import smith.lib.alerts.dialog.callbacks.OnDrawPatternCallBack;

/**
 * The beautiful and safest class of SDialog lib.
 * this class create a lock view SDialog using pattern that alerts the user to unlock something.
 */
public class PatternSDialog extends SDialog {

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public PatternSDialog(Context context) {
    	super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_pattern, null);
        init();
        
        setCancelable(false);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As Int resource (R.drawable.icon).
     */
    public void setIconResource(@DrawableRes int icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageResource(icon);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As {@link Drawable}.
     */
    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageDrawable(icon);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As {@link Bitmap}.
     */
    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageBitmap(icon);
    }

    /**
     * Set title from string to current SDialog.
     *
     * @param title String title.
     */
    public void setTitle(String title) {
        b.title.setText(title);
    }

    /**
     * Set text from int resource to current SDialog.
     *
     * @param resTitle Int resource title (R.string.title).
     */
    public void setTitle(@StringRes int resTitle) {
        b.title.setText(resTitle);
    }

    /**
     * Add functional interface to get the pattern and event being triggered while drawing the pattern.
     * @param callback a callback using {@link OnDrawPatternCallBack}.
     */
    public void setOnDrawPatternCallBack(OnDrawPatternCallBack callback) {
    	b.pattern.addPatternLockListener(new PatternLockViewListener() {
            @Override public void onCleared() { callback.onDrawingCleared(); }
            @Override public void onProgress(List<PatternLockView.Dot> dots) {
                callback.onDrawingProgress(PatternLockUtils.patternToString(b.pattern, dots));
            }
            @Override public void onComplete(List<PatternLockView.Dot> dots) {
                callback.onDrawingCompleted(PatternLockUtils.patternToString(b.pattern, dots));
            }
            @Override public void onStarted() { callback.onDrawingStarted(); }
        });
    }
    
    public void setPatternMode(int patternMode) {
    	b.pattern.setViewMode(patternMode);
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
    
    public int getPatternColor() {
        return accentColor;
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
    public void show(long duration) {
        update();
        super.show(duration);
    }
    
    public void dismiss(int duration) {
    	new CountDownTimer(duration, 1) {
            @Override public void onTick(long dur) {}
            @Override public void onFinish() { dismiss(); }
        }.start();
    }
    
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.pattern.setNormalStateColor(accentColor);
    }
}