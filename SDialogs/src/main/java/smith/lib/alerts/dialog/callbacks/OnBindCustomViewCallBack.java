package smith.lib.alerts.dialog.callbacks;

import android.view.View;

/**
 * Binding view callback to do logic when attaching a view to CustomSDialog.
 */
@FunctionalInterface
public interface OnBindCustomViewCallBack {
    /**
     * Triggered whenever a new view/layout been attached to CustomSDialog.
     * @param customView the view that been attached to th SDialog.
     */
    void onBindCustomView(View customView);
}