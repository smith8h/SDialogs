package smith.lib.alerts.dialog.callbacks

/**
 * On multiple items selected callback for MultiSelectSDialog.
 */
interface OnMultiSelectCallBack {
    /**
     * triggered on selecting items from the list and returns:
     * @param selectedItems as a list of items that been selected.
     */
    fun onMultiSelect(selectedItems: List<Map<String, Any>>)
}
