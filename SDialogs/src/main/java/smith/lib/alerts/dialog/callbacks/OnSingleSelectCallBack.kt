package smith.lib.alerts.dialog.callbacks

/**
 * Single item selection callback to get the value selected.
 */
interface OnSingleSelectCallBack {
    /**
     * triggered when selecting an item from the selection.
     * @param itemId the id of that selected item.
     * @param itemText the text of that selected item.
     */
    fun onSelect(itemId: Int, itemText: String)
}
