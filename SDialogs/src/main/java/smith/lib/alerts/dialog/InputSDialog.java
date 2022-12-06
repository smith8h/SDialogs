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
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import smith.lib.alerts.dialog.callbacks.OnInputClickCallBack;

public class InputSDialog extends SDialog {
    
    public InputSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_input, null);
        init();
    }
    
    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
    }

    public void setText(String text) {
        ((TextView) dialogView.findViewById(R.id.text)).setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }
    
    public void setPositiveButtonAction(String positive, OnInputClickCallBack callback) {
        ((TextView) dialogView.findViewById(R.id.positive)).setText(positive);
        ((TextView) dialogView.findViewById(R.id.positive)).setOnClickListener(v-> {
            callback.onClick(((TextInputEditText) dialogView.findViewById(R.id.inputed)).getText().toString());
            dismiss();
        });
    }
    
    public void setNegativeButtonText(String negative) {
        ((TextView) dialogView.findViewById(R.id.negative)).setText(negative);
        ((TextView) dialogView.findViewById(R.id.negative)).setOnClickListener(v-> dismiss());
    }
    
    public void setInputFieldHint(String hint) {
        ((TextInputLayout) dialogView.findViewById(R.id.input)).setHint(hint);
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
        super.show(dur);
    }
    
    private void update() {
        if (theme == SYSTEM_THEME) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == DARK_THEME) darkThemeColors();
        else if (theme == LIGHT_THEME) lightThemeColors();
        
        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        ((TextView) dialogView.findViewById(R.id.text)).setTextColor(textColor);
        ((TextView) dialogView.findViewById(R.id.positive)).setTextColor(accentColor);
        ((TextView) dialogView.findViewById(R.id.negative)).setTextColor(accentColor);
        
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
        
        ((TextInputLayout) dialogView.findViewById(R.id.input)).setBoxStrokeColorStateList(new ColorStateList(states, colors));
        ((TextInputLayout) dialogView.findViewById(R.id.input)).setDefaultHintTextColor(new ColorStateList(states, colors));
        ((TextInputLayout) dialogView.findViewById(R.id.input)).setHintTextColor(new ColorStateList(states, colors));
        ((TextInputEditText) dialogView.findViewById(R.id.inputed)).setTextColor(textColor);
        ((TextInputEditText) dialogView.findViewById(R.id.inputed)).setHintTextColor(hintColor);
    }
}
