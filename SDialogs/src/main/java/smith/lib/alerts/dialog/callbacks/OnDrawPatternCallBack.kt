package smith.lib.alerts.dialog.callbacks

interface OnDrawPatternCallBack {
    fun onStartDrawing();
    fun onCompleteDrawing(pattern: String);
    fun onClearDrawing();
}