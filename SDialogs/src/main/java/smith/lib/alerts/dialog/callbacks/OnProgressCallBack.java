package smith.lib.alerts.dialog.callbacks;

/**
 * on progress changed callback for ProgressSDialog.
 */
public interface OnProgressCallBack {
    /**
     * triggered when progress is continuing and returns:
     *
     * @param progress the current progress as int.
     * @param percent  the progress percent from 100% as int.
     */
    void onProgress(int progress, int percent);
    /**
     * triggered as far as the progress is completed.
     */
    void onFinish();
}
