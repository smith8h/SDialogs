package smith.lib.alerts.dialog.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.MultiSelectSDialog;

public class SMultiSelectAdapter extends RecyclerView.Adapter<SMultiSelectAdapter.ViewHolder> {

    List<Map<String, Object>> data = new ArrayList<>();
    List<Map<String, Object>> checkedList = new ArrayList<>();
    
    MultiSelectSDialog sdialog;
    
    public SMultiSelectAdapter(List<Map<String, Object>> itmData, MultiSelectSDialog dialog) {
        data = itmData;
        sdialog = dialog;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_checkboxes, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int p) {

        holder.choice.setText(data.get(p).get("text").toString());
        holder.choice.setTextColor(sdialog.getTextColor());
        holder.choice.setButtonTintList(ColorStateList.valueOf(sdialog.getAccentColor()));
        
        boolean checked = (boolean) data.get(p).get("checked");
        if (checked) holder.choice.setChecked(true);
        else holder.choice.setChecked(false);
        
        if (!contains(checkedList, data.get(p)) && checked) checkedList.add(data.get(p));
        if (contains(checkedList, data.get(p)) && !checked) checkedList.remove(data.get(p));
            
        holder.choice.setOnCheckedChangeListener((button, isChecked) -> {
            if (!contains(checkedList, data.get(p)) && isChecked) checkedList.add(data.get(p));
            if (contains(checkedList, data.get(p)) && !isChecked) checkedList.remove(data.get(p));
        });
        
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    
    private boolean contains(List<Map<String, Object>> list, Map<String, Object> item) {
        if (list.contains(item)) return true;
        else return false;
    }
    
    public List<Map<String, Object>> getCheckedItems() {
        return checkedList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox choice;
        private LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            choice = (CheckBox) v.findViewById(R.id.choice);
            main = (LinearLayout) v.findViewById(R.id.main);
        }
    }
}
