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

public class LoadingSDialog extends SDialog {

    public LoadingSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_loading, null);
        init();
    }

    public void setTitle(String title) {
        b.title.setText(title);
    }

    public void setText(String text) {
        b.text.setText(text);
    }
    
    public void setText(int text) {
        b.text.setText(text);
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
        super.show(dur);
    }
        
    private void update() {
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();

        setBackgroundColor(b.main, backgroundColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.loading.setIndeterminateTintList(ColorStateList.valueOf(accentColor));
    }
}
