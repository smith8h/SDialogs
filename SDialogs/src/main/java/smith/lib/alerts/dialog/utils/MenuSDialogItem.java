package smith.lib.alerts.dialog.utils;

import android.graphics.drawable.Drawable;

/**
 * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
 * @noinspection ClassCanBeRecord
 */
public class MenuSDialogItem {

    private final Drawable icon;
    private final String title;

    /**
     * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
     * @param icon the icon of the item as {@link Drawable}.
     * @param title the title of the item as {@link String}.
     */
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