package smith.lib.alerts.dialog.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.annotation.StringDef;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import smith.lib.alerts.dialog.ItemsSDialog;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;

public class SRadiosAdapter extends RecyclerView.Adapter<SRadiosAdapter.ViewHolder> {

    List<HashMap<String, Object>> data = new ArrayList<>();

    OnItemClickCallBack callback;

    ItemsSDialog sdialog;

    public SRadiosAdapter(
            List<HashMap<String, Object>> itmData, OnItemClickCallBack call, ItemsSDialog dialog) {
        data = itmData;
        callback = call;
        sdialog = dialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_radios, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int p) {

        holder.choice.setText(data.get(p).get("text").toString());
        holder.choice.setTextColor(sdialog.getTextColor());

        int[][] states = new int[][] {
            new int[] {-android.R.attr.state_checked},
            new int[] {android.R.attr.state_checked}
        };
        int[] colors = new int[] {
            sdialog.getTextColor(),
            sdialog.getAccentColor()
        };

        holder.choice.setCompoundDrawableTintList(new ColorStateList(states, colors));

        holder.main.setOnClickListener(v -> {
            callback.onItemClick(stringData.get(p), "" + stringData.get(p), p);
            sdialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return stringData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RadioButton choice;
        private LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            choice = (RadioButton) v.findViewById(R.id.choice);
            main = (LinearLayout) v.findViewById(R.id.main);
        }
    }
}
