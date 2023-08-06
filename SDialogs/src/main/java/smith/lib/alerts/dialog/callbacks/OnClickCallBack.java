package smith.lib.alerts.dialog.callbacks;

/**
 * On click callback for several SDialog types.
 */
@FunctionalInterface
public interface OnClickCallBack {
    /**
     * Triggered whenever buttons clicked.
     */
    void onClick();
}