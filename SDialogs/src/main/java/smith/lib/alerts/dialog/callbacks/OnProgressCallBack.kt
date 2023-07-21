package smith.lib.alerts.dialog.callbacks

interface OnProgressCallBack {
    fun onProgress(progress: Int, percent: Int)
    fun onFinish()
}
