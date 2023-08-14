package smith.lib.alerts.dialog.utils;

import android.graphics.drawable.Drawable;

public class MenuSDialogItem {
    private final Drawable icon;
    private final String title;

    public MenuSDialogItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
