package smith.lib.alerts.dialog.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

/**
 * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
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

    /**
     * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
     * @param context current {@link Context} (or {@link Activity}).
     * @param icon the icon of the item as int {@link DrawableRes}.
     * @param title the title of the item as {@link String}.
     */
    public MenuSDialogItem(Context context, @DrawableRes int icon, String title) {
        this.icon = ContextCompat.getDrawable(context, icon);
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}