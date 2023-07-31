package smith.lib.alerts.dialog.callbacks

/**
 * On item selected callback for ItemsSDialog.
 */
interface OnItemClickCallBack {
    /**
     * triggered on selecting item from the list and returns:
     * @param itemValue the value of that item as string.
     * @param itemIndex the id of that item as int.
     */
    fun onItemClick(itemValue: String, itemIndex: Int)
}