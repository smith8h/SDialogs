package smith.lib.alerts.dialog.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import smith.lib.alerts.dialog.ItemsSDialog;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;

public class SItemsAdapter extends RecyclerView.Adapter<SItemsAdapter.ViewHolder> {

    private final List<String> data;
    private final OnItemClickCallBack callback;
    private final ItemsSDialog sdialog;

    public SItemsAdapter(List<String> data, OnItemClickCallBack callback, ItemsSDialog sdialog) {
        this.data = data;
        this.callback = callback;
        this.sdialog = sdialog;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_items, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        holder.text.setText(data.get(p));
        holder.text.setTextColor(sdialog.getAccentColor());
        holder.main.setOnClickListener(v -> {
            if (callback != null) callback.onItemClick(data.get(p), p);
            sdialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout main;

        public ViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            main = view.findViewById(R.id.main);
        }
    }
}
