package smith.lib.alerts.dialog.adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.Contract;
import java.util.*;
import smith.lib.alerts.dialog.MultiSelectSDialog;
import smith.lib.alerts.dialog.R;

public class SMultiSelectAdapter extends RecyclerView.Adapter<SMultiSelectAdapter.ViewHolder> {

    private final List<Map<String, Object>> data;
    private final MultiSelectSDialog sdialog;

    private final List<Map<String, Object>> checkedList = new ArrayList<>();

    public SMultiSelectAdapter(List<Map<String, Object>> data, MultiSelectSDialog sdialog) {
        this.data = data;
        this.sdialog = sdialog;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_checkboxes, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        holder.choice.setText(Objects.requireNonNull(data.get(p).get("text")).toString());
        holder.choice.setTextColor(sdialog.getTextColor());
        holder.choice.setButtonTintList(ColorStateList.valueOf(sdialog.getAccentColor()));
        boolean checked = (boolean) Objects.requireNonNull(data.get(p).get("checked"));
        holder.choice.setChecked(checked);

        if (!contains(checkedList, data.get(p)) && checked) checkedList.add(data.get(p));
        if (contains(checkedList, data.get(p)) && !checked) checkedList.remove(data.get(p));
        holder.choice.setOnCheckedChangeListener((button, isChecked) -> {
            if (!contains(checkedList, data.get(p)) && isChecked) checkedList.add(data.get(p));
            if (contains(checkedList, data.get(p)) && !isChecked) checkedList.remove(data.get(p));
        });
    }



    @Override public int getItemCount() {
        return data.size();
    }

    @Contract(pure = true)
    private boolean contains(@NonNull List<Map<String, Object>> list, Map<String, Object> item) {
        return list.contains(item);
    }

    public List<Map<String, Object>> getCheckedItems() {
        return checkedList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox choice;
        LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            choice = v.findViewById(R.id.choice);
            main = v.findViewById(R.id.main);
        }
    }
}