package smith.lib.alerts.dialog.adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.SingleSelectSDialog;
import smith.lib.alerts.dialog.callbacks.OnSingleSelectCallBack;

public class SSingleSelectAdapter extends RecyclerView.Adapter<SSingleSelectAdapter.ViewHolder> {

    private final List<Map<String, Object>> data;
    private final OnSingleSelectCallBack callback;
    private final SingleSelectSDialog sdialog;

    public SSingleSelectAdapter(List<Map<String, Object>> data, OnSingleSelectCallBack callback, SingleSelectSDialog sdialog) {
        this.data = data;
        this.callback = callback;
        this.sdialog = sdialog;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_radios, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        holder.choice.setText(Objects.requireNonNull(data.get(p).get("text")).toString());
        holder.choice.setTextColor(sdialog.getTextColor());
        holder.choice.setButtonTintList(ColorStateList.valueOf(sdialog.getAccentColor()));
        holder.choice.setChecked((Boolean) Objects.requireNonNull(data.get(p).get("checked")));
        holder.choice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (callback != null)
                callback.onSelect((int) Objects.requireNonNull(data.get(p).get("id")),
                        Objects.requireNonNull(data.get(p).get("text")).toString());
            sdialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton choice;
        LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            choice = v.findViewById(R.id.sdialog_choice);
            main = v.findViewById(R.id.sdialog_main);
        }
    }
}