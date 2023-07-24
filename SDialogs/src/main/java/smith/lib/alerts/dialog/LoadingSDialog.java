package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;

public class LoadingSDialog extends SDialog {

    @SuppressLint("InflateParams")
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
    public void show(long duration) {
        setCancelable(false);
        update();
        super.show(duration);
    }
        
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.title.setTextColor(titleColor);
        b.text.setTextColor(textColor);
        b.loading.setIndeterminateTintList(ColorStateList.valueOf(accentColor));
    }
}
