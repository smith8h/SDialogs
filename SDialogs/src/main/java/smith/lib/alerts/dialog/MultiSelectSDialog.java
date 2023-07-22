package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.*;
import java.util.*;

import smith.lib.alerts.dialog.adapters.SMultiSelectAdapter;
import smith.lib.alerts.dialog.callbacks.OnMultiSelectCallBack;

public class MultiSelectSDialog extends SDialog {
    
    private SMultiSelectAdapter adapter;
    private final List<Map<String, Object>> data = new ArrayList<>();
    private Map<String, Object> item = new HashMap<>();
    
    @SuppressLint("InflateParams")
    public MultiSelectSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_items, null);
        init();
    }
    
    public void setIconResource(int icon) {
        b.icon.setVisibility(View.VISIBLE);
    	b.icon.setImageResource(icon);
    }
    
    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
    	b.icon.setImageDrawable(icon);
    }
    
    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
    	b.icon.setImageBitmap(icon);
    }
    
    public void setTitle(String title) {
        b.title.setText(title);
    }
    
    public void setPositiveButton(String positive, OnMultiSelectCallBack callback) {
         b.holder.setVisibility(View.VISIBLE);
         b.positive.setText(positive);
         b.positive.setOnClickListener(v-> {
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
            int ids = (int) data.get(i).get(KEY_ITEM_ID);
            if (ids == id) {
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
            Map<String, Object> item = data.get(i);
            if (Objects.requireNonNull(item.get("text")).toString().equals(itemText))
                data.remove(item);
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
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.positive.setTextColor(accentColor);
        
        adapter = new SMultiSelectAdapter(data, this);
        b.recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        b.recycler.setAdapter(adapter);
    }
}
