package smith.lib.alerts.dialog.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import smith.lib.alerts.dialog.MenuSDialog;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;
import smith.lib.alerts.dialog.utils.MenuSDialogItem;

public class SMenuAdapter extends RecyclerView.Adapter<SMenuAdapter.ViewHolder> {

    private final List<MenuSDialogItem> data;
    private final OnItemClickCallBack callback;
    private final MenuSDialog sdialog;

    public SMenuAdapter(List<MenuSDialogItem> data, OnItemClickCallBack callback, MenuSDialog sdialog) {
        this.data = data;
        this.callback = callback;
        this.sdialog = sdialog;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_menu, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        MenuSDialogItem item = data.get(p);

        holder.icon.setImageDrawable(item.getIcon());
        holder.icon.setColorFilter(sdialog.getAccentColor());

        holder.text.setText(item.getTitle());
        holder.text.setTextColor(sdialog.getAccentColor());

        holder.main.setOnClickListener(v -> {
            if (callback != null) callback.onItemClick(p, item);
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
        ImageView icon;

        public ViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            main = view.findViewById(R.id.main);
            icon = view.findViewById(R.id.icon);
        }
    }
}
