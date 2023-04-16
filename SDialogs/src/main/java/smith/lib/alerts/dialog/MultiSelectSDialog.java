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


package smith.lib.alerts.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.tv.TvView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import smith.lib.alerts.dialog.adapters.SMultiSelectAdapter;
import smith.lib.alerts.dialog.callbacks.OnMultiSelectCallBack;
import smith.lib.views.recyclerview.SRecyclerView;

public class MultiSelectSDialog extends SDialog {
    
    private SMultiSelectAdapter adapter;
    private List<Map<String, Object>> data = new ArrayList<>();
    private Map<String, Object> item = new HashMap<>();
    
    public MultiSelectSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_items, null);
        init();
    }
    
    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
    }
    
    public void setPositiveButton(String positive, OnMultiSelectCallBack callback) {
         ((LinearLayout) dialogView.findViewById(R.id.holder)).setVisibility(View.VISIBLE);
         ((TextView) dialogView.findViewById(R.id.positive)).setText(positive);
         ((TextView) dialogView.findViewById(R.id.positive)).setOnClickListener(v-> {
             if (adapter != null) callback.onMultiSelect(adapter.getCheckedItems());
             dismiss();
         });
    }
    
    public void addItem(int id, String text, boolean checked) {
        item = new HashMap<>();
        item.put(KEY_ITEM_ID, id);
        item.put(KEY_ITEM_TEXT, text);
        item.put(KEY_ITEM_CHECKED, checked);
        data.add(item);
        update();
    }
    
    public void setCheckedItem(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get(KEY_ITEM_ID).equals(id)) {
                item = data.get(i);
                data.remove(i);
                item.put(KEY_ITEM_CHECKED, true);
                data.add(i, item);
                break;
            }
        }
        update();
    }
    
    public void removeItem(String itemText) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("text").toString().equals(itemText)) data.remove(i);
        }
        update();
    }
    
    public void removeItem(int index) {
        data.remove(index);
        update();
    }
    
    public void setAccentColor(int color) {
        accentColor = color;
    }

    public void setAccentColor(String color) {
        accentColor = Color.parseColor(color);
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
    
    public int getAccentColor() {
        return accentColor;
    }
    
    public int getTitleColor() {
        return titleColor;
    }
    
    public int getBackgroundColor() {
        return backgroundColor;
    }
    
    public int getTextColor() {
        return textColor;
    }
    
    public List<Map<String, Object>> getItemsList() {
        return data;
    }
    
    @Override
    public void show() {
        update();
        super.show();
    }
    
    @Override
    public void show(long dur) {
        update();
        super.show();
    }
    
    private void update() {
        if (theme == SYSTEM_THEME) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == DARK_THEME) darkThemeColors();
        else if (theme == LIGHT_THEME) lightThemeColors();
        
        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        ((TextView) dialogView.findViewById(R.id.positive)).setTextColor(accentColor);
        
        adapter = new SMultiSelectAdapter(data, this);
        ((SRecyclerView) dialogView.findViewById(R.id.recycler))
            .setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
        ((SRecyclerView) dialogView.findViewById(R.id.recycler)).setAdapter(adapter);
    }
}
