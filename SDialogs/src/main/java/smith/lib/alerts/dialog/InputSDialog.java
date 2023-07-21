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
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;

    public class InputSDialog extends SDialog {

    public InputSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_input, null);
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

    public void setText(String text) {
        b.text.setVisibility(View.VISIBLE);
        b.text.setText(text);
    }
    
    public void setText(int text) {
        b.text.setVisibility(View.VISIBLE);
        b.text.setText(text);
    }
    
    public void setPositiveButtonAction(String positive, OnInputClickCallBack callback) {
        b.positive.setText(positive);
        b.positive.setOnClickListener(v-> {
            callback.onClick(b.inputed.getText().toString());
            dismiss();
        });
    }
    
    public void setNegativeButtonText(String negative) {
        b.negative.setText(negative);
        b.negative.setOnClickListener(v-> dismiss());
    }
    
    public void setInputFieldHint(String hint) {
        b.input.setHint(hint);
    }
    
    public void setInputFieldText(String text) {
    	b.input.getEditText().setText(text);
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
    
    public int getHintColor() {
        return hintColor;
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
        
        setBackgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.positive.setTextColor(accentColor);
        b.negative.setTextColor(accentColor);
        
        int[][] states = new int[][] {
            new int[] { android.R.attr.state_focused },
            new int[] { android.R.attr.state_hovered },
            new int[] { android.R.attr.state_enabled },
            new int[] { }
        };
        int[] colors = new int[] {
            accentColor,
            accentColor,
            accentColor,
            hintColor
        };
        
        b.input.setBoxStrokeColorStateList(new ColorStateList(states, colors));
        b.input.setDefaultHintTextColor(new ColorStateList(states, colors));
        b.input.setHintTextColor(new ColorStateList(states, colors));
        b.inputed.setTextColor(textColor);
        b.inputed.setHintTextColor(hintColor);
    }
}
