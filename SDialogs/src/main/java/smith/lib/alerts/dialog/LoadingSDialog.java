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
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingSDialog extends SDialog {

    public LoadingSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_loading, null);
        init();
    }

    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
    }

    public void setText(String text) {
        ((TextView) dialogView.findViewById(R.id.text)).setText(text);
    }

    public void setLoadingColor(int color) {
        loadingColor = color;
    }

    public void setLoadingColor(String color) {
        loadingColor = Color.parseColor(color);
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
    
    @Override
    public void show() {
        setCancelable(false);
        updateColors();
        super.show();
    }
    
    @Override
    public void show(long dur) {
        setCancelable(false);
        updateColors();
        super.show(dur);
    }
        
    private void updateColors() {
        if (theme == SYSTEM_THEME) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == DARK_THEME) darkThemeColors();
        else if (theme == LIGHT_THEME) lightThemeColors();

        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        ((TextView) dialogView.findViewById(R.id.text)).setTextColor(textColor);
        ((ProgressBar) dialogView.findViewById(R.id.loading)).setIndeterminateTintList(ColorStateList.valueOf(loadingColor));
    }
}
