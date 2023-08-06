package smith.lib.alerts.dialog.callbacks;

import java.util.*;

/**
 * On multiple items selected callback for MultiSelectSDialog.
 */
@FunctionalInterface
public interface OnMultiSelectCallBack {
    /**
     * triggered on selecting items from the list and returns:
     *
     * @param selectedItems as a list of items that been selected.
     */
    void onMultiSelect(List<Map<String, Object>> selectedItems);
}
