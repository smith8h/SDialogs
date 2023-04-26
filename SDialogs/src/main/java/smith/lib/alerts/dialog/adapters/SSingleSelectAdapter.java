    /*
     *
     *
     *    THIS LIBRARY CREATED BY HUSSEIN SHAKIR (SMITH)
     *
     *	TELEGRAM : @SMITHDEV
     *	YOUTUBE : HUSSEIN SMITH (@SMITH8H)
     *
     *	YOU GUYS ARE NOT ALLOWED TO MODIFY THIS LIBRARY,
     *	WITHOT ANY PERMISSION FROM ME PERSONALLY..
     *	ALL RIGHTS RESERVED © HUSSEIN SHAKIR, Dec 2022.
     *
     *
     */


package smith.lib.alerts.dialog.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import smith.lib.alerts.dialog.SDialog;
import smith.lib.alerts.dialog.SingleSelectSDialog;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.callbacks.OnSingleSelectCallBack;

public class SSingleSelectAdapter extends RecyclerView.Adapter<SSingleSelectAdapter.ViewHolder> {

    List<Map<String, Object>> data;

    OnSingleSelectCallBack callback;

    SingleSelectSDialog sdialog;

    public SSingleSelectAdapter(List<Map<String, Object>> itmData, OnSingleSelectCallBack call, SingleSelectSDialog dialog) {
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
        holder.choice.setButtonTintList(ColorStateList.valueOf(sdialog.getAccentColor()));
        
        if ((boolean) data.get(p).get("checked")) holder.choice.setChecked(true);
        else holder.choice.setChecked(false);
        
        holder.choice.setOnCheckedChangeListener((button, isChecked) -> {
            if (callback != null) callback.onSelect((int) data.get(p).get("id"), data.get(p).get("text").toString());
            sdialog.dismiss();
        });
        
    }

    @Override
    public int getItemCount() {
        return data.size();
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
