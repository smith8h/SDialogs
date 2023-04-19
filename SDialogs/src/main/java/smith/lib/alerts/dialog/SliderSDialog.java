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
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.*;
import com.google.android.material.slider.Slider;
import android.graphics.Color;
import smith.lib.alerts.dialog.callbacks.OnSlideCallBack;

public class SliderSDialog extends SDialog {
    
    private final int MAX = 100;
    private final int MIN = 0;
    
    public SliderSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_seek, null);
        init();
        
        Slider s = (Slider) dialogView.findViewById(R.id.seek);
        s.setValueFrom(MIN);
        s.setValueTo(MAX);
        
        int height = s.getTrackHeight();
        s.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override public void onStartTrackingTouch(Slider slider) { slider.setTrackHeight(50); }
            @Override public void onStopTrackingTouch(Slider slider) { slider.setTrackHeight(height); }
        });
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
        ((TextView) dialogView.findViewById(R.id.text)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }
    
    public void setText(int text) {
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }
    
    public void setMin(float min) {
        ((Slider) dialogView.findViewById(R.id.seek)).setValueFrom(min);
        ((Slider) dialogView.findViewById(R.id.seek)).setValue(min);
    }
    
    public void setMax(float max) {
        ((Slider) dialogView.findViewById(R.id.seek)).setValueTo(max);
    }
    
    public void setValue(float value) {
        ((Slider) dialogView.findViewById(R.id.seek)).setValue(value);
    }
    
    public void setStepBy(float stepBy) {
    	((Slider) dialogView.findViewById(R.id.seek)).setStepSize(stepBy);
    }
    
    public void setPositiveButtonAction(String positive, OnSlideCallBack callback) {
        ((TextView) dialogView.findViewById(R.id.positive)).setText(positive);
        ((TextView) dialogView.findViewById(R.id.positive)).setOnClickListener(v-> {
            callback.onValueSelected(((Slider) dialogView.findViewById(R.id.seek)).getValue());
            dismiss();
        });
    }
    
    public void setNegativeButtonText(String negative) {
        ((TextView) dialogView.findViewById(R.id.negative)).setText(negative);
        ((TextView) dialogView.findViewById(R.id.negative)).setOnClickListener(v-> dismiss());
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
    
    public float getValue() {
        return ((Slider) dialogView.findViewById(R.id.seek)).getValue();
    }
    
    public float getMinValue() {
        return ((Slider) dialogView.findViewById(R.id.seek)).getValueFrom();
    }
    
    public float getMaxValue() {
        return ((Slider) dialogView.findViewById(R.id.seek)).getValueTo();
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
        super.show();
    }
    
    private void update() {
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();

        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((ImageView) dialogView.findViewById(R.id.icon)).setColorFilter(iconColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        ((TextView) dialogView.findViewById(R.id.text)).setTextColor(textColor);
        ((TextView) dialogView.findViewById(R.id.positive)).setTextColor(accentColor);
        ((TextView) dialogView.findViewById(R.id.negative)).setTextColor(accentColor);
        
        int[][] states = new int[][] {
            new int[] { android.R.attr.state_active },
            new int[] { android.R.attr.state_hovered },
            new int[] { android.R.attr.state_enabled },
            new int[] { -android.R.attr.state_active }
        };
        int[] colors = new int[] {
            accentColor,
            accentColor,
            accentColor,
            hintColor
        };
        
        ((Slider) dialogView.findViewById(R.id.seek)).setThumbTintList(ColorStateList.valueOf(accentColor));
        ((Slider) dialogView.findViewById(R.id.seek)).setTrackTintList(new ColorStateList(states, colors));
    }
}
