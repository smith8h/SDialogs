package smith.lib.alerts.dialog.callbacks;

/**
 * On input data callback for InputSDialog.
 */
@FunctionalInterface
public interface OnInputClickCallBack {
    /**
     * triggered when clicking positive button in InputSDialog.
     *
     * @param input return the inputted data as string.
     */
    void onClick(String input);
}
