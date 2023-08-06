package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.*;
import java.util.List;
import co.encept.patternlockview.PatternLockView;
import co.encept.patternlockview.listener.PatternLockViewListener;
import co.encept.patternlockview.utils.PatternLockUtils;
import smith.lib.alerts.dialog.callbacks.OnDrawPatternCallBack;

/**
 * The beautiful and safest class of SDialog lib.
 * this class create a lock view SDialog using pattern that alerts the user to unlock something.
 */
@SuppressWarnings({"unused"})
public class PatternSDialog extends SDialog {

    private int height;
    private int width;

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public PatternSDialog(Context context) {
    	super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_pattern, null);
        init();

        height = (int) utils.dp(280);
        width = (int) utils.dp(280);

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

    /**
     * Set the pattern mode after completing the drawing.
     * @param patternMode modes options are
     *     <ul>
     *         <li>{@link SDialog#PATTERN_MODE_CORRECT} for correct pattern.</li>
     *         <li>{@link SDialog#PATTERN_MODE_WRONG} for wrong pattern.</li>
     *     </ul>
     */
    public void setPatternMode(int patternMode) {
    	b.pattern.setViewMode(patternMode);
    }

    public void setPatternViewHeight(int height) {
        this.height = (int) utils.dp(height);
    }

    public void setPatternViewWidth(int width) {
        this.width = (int) utils.dp(width);
    }

    /**
     * @return Accent color of current SDialog showed as light theme or dark theme.
     */
    public int getAccentColor() {
        return accentColor;
    }

    /**
     * @return Title color of current SDialog showed as light theme or dark theme.
     */
    public int getTitleColor() {
        return titleColor;
    }

    /**
     * @return Background color of current SDialog showed as light theme or dark theme.
     */
    public int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @return Pattern color of current SDialog showed as light theme or dark theme.
     */
    public int getPatternColor() {
        return accentColor;
    }

    /**
     * This setter wont work on pattern SDialog as it designed for security.
     * it will be cancelled as far as you draw a pattern.
     * <p>
     *     Don't forget to set a {@link OnDrawPatternCallBack} listener to get pattern drawn and deal with it.
     * </p>
     */
    @Override
    public void setCancelable(boolean cancelable) {}

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
        b.pattern.setNormalStateColor(accentColor);
        b.pattern.setLayoutParams(new LinearLayout.LayoutParams(width, height, 0));
    }
}