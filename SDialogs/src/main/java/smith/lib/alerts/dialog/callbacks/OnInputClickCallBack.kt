package smith.lib.alerts.dialog.callbacks

/**
 * On input data callback for InputSDialog.
 */
interface OnInputClickCallBack {
    /**
     * triggered when clicking positive button in InputSDialog.
     * @param input return the inputted data as string.
     */
    fun onClick(input: String)
}
