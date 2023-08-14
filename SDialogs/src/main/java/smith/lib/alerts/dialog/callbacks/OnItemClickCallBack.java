package smith.lib.alerts.dialog.callbacks;

import smith.lib.alerts.dialog.utils.MenuSDialogItem;

/**
 * On item selected callback for ItemsSDialog.
 */
@FunctionalInterface
public interface OnItemClickCallBack {
    /**
     * triggered on selecting item from the list and returns:
     *
     * @param index the index of that item clicked.
     * @param item the item clicked on the menu.
     */
    void onItemClick(int index, MenuSDialogItem item);
}