package smith.lib.alerts.dialog.callbacks;

/**
 * On dismiss callback useful for dismissing SDialogs.
 */
@FunctionalInterface
public interface OnDismissCallBack {
    /**
     * Triggered whenever the SDialogs are dismissed.
     */
    void onDismiss();
}