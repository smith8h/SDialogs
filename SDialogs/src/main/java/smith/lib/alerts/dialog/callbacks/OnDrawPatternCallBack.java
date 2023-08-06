package smith.lib.alerts.dialog.callbacks;

/**
 * On drawing pattern callback for PatternSDialog.
 */
public interface OnDrawPatternCallBack {
    /**
     * triggered whenever drawing started (immediately when you touch a dot and move you finger).
     */
    void onDrawingStarted();

    /**
     * triggered when drawing is on progress.
     *
     * @param pattern returns the current pattern progress as string of numbers represent the patterns.
     */
    void onDrawingProgress(String pattern);

    /**
     * triggered whenever the drawing is finished after releasing your finger.
     *
     * @param pattern returns the full pattern as string of numbers represent the patterns.
     */
    void onDrawingCompleted(String pattern);

    /**
     * triggered after releasing your finger and the drawn pattern cleared.
     */
    void onDrawingCleared();
}