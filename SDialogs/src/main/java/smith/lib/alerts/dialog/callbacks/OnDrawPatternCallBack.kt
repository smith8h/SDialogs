package smith.lib.alerts.dialog.callbacks

/**
 * On drawing pattern callback for PatternSDialog.
 */
interface OnDrawPatternCallBack {
    /**
     * triggered whenever drawing started (immediately when you touch a dot and move you finger).
     */
    fun onDrawingStarted()

    /**
     * triggered when drawing is on progress.
     * @param pattern returns the current pattern progress as string of numbers represent the patterns.
     */
    fun onDrawingProgress(pattern: String)

    /**
     * triggered whenever the drawing is finished after releasing your finger.
     * @param pattern returns the full pattern as string of numbers represent the patterns.
     */
    fun onDrawingCompleted(pattern: String)

    /**
     * triggered after releasing your finger and the drawn pattern cleared.
     */
    fun onDrawingCleared()
}