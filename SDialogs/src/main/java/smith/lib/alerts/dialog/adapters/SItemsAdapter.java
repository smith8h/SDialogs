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

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.annotation.StringDef;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import smith.lib.alerts.dialog.ItemsSDialog;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;

public class SItemsAdapter extends RecyclerView.Adapter<SItemsAdapter.ViewHolder> {

    List<String> data = new ArrayList<>();
    
    OnItemClickCallBack callback;
    
    ItemsSDialog sdialog;
    
    public SItemsAdapter(List<String> strData, OnItemClickCallBack call, ItemsSDialog dialog) {
        data = strData;
        callback = call;
        sdialog = dialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_items, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int p) {
        
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text);
            main = (LinearLayout) v.findViewById(R.id.main);
        }
    }
}
