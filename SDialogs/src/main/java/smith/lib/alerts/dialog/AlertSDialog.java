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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;
import smith.lib.alerts.dialog.callbacks.OnClickCallBack;

public class AlertSDialog extends SDialog {

    @SuppressLint("InflateParams")
    public AlertSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_alert, null);
        init();
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

    public void setText(CharSequence text) {
        b.text.setText(text);
    }
    
    public void setText(int text) {
    	b.text.setText(text);
    }

    public void setPositiveButton(String positive, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.positive.setVisibility(View.VISIBLE);
        b.positive.setText(positive);
        b.positive.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setNegativeButton(String negative, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.negative.setVisibility(View.VISIBLE);
        b.negative.setText(negative);
        b.negative.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }

    public void setNeutralButton(String neutral, OnClickCallBack callback) {
        b.holder.setVisibility(View.VISIBLE);
        b.neutral.setVisibility(View.VISIBLE);
        b.neutral.setText(neutral);
        b.neutral.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
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
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();
        
        setBackgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.positive.setTextColor(accentColor);
        b.negative.setTextColor(accentColor);
        b.neutral.setTextColor(accentColor);
    }
}
