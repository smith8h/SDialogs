package smith.lib.alerts.dialog.callbacks;

import java.util.List;
import java.util.Map;

public interface OnMultiSelectCallBack {
    public void onMultiSelect(List<Map<String, Object>> itemsList, String idKey, String textKey, String checkedKey);
}
