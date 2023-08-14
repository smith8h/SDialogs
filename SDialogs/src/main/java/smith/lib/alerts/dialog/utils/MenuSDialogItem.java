package smith.lib.alerts.dialog.utils;

import android.graphics.drawable.Drawable;

/**
 * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
 * @param icon the icon of the item as {@link Drawable}.
 * @param title the title of the item as {@link String}.
 */
public record MenuSDialogItem(Drawable icon, String title) {}
