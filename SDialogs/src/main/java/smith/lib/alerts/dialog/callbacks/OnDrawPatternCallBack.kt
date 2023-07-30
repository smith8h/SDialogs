package smith.lib.alerts.dialog.callbacks

interface OnDrawPatternCallBack {
    fun onDrawingStarted()
    fun onDrawingProgress(pattern: String)
    fun onDrawingCompleted(pattern: String)
    fun onDrawingCleared()
}