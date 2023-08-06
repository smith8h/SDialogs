package smith.lib.alerts.dialog.callbacks;

/**
 * Slider callback to get the value selected.
 */
@FunctionalInterface
public interface OnSlideCallBack {
    /**
     * triggered when clicking on ok button to get the value selected from the slider.
     *
     * @param value The value returned from slider as Float.
     */
    void onValueSelected(float value);
}
