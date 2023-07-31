package smith.lib.alerts.dialog.callbacks

import android.view.View

/**
 * Binding view callback to do logic when attaching a view to CustomSDialog.
 */
interface OnBindCustomViewCallBack {
    /**
     * Triggered whenever a new view/layout been attached to CustomSDialog.
     * @param customView the view that been attached to th SDialog.
     */
    fun onBindCustomView(customView: View)
}