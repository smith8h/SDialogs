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
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import smith.lib.alerts.dialog.adapters.SSingleSelectAdapter;
import smith.lib.alerts.dialog.callbacks.OnSingleSelectCallBack;
import smith.lib.views.recyclerview.SRecyclerView;

public class SingleSelectSDialog extends SDialog {
    
    private SSingleSelectAdapter adapter;
    private List<Map<String, Object>> data = new ArrayList<>();
    private Map<String, Object> item = new HashMap<>();
    private OnSingleSelectCallBack callback;
    
    public SingleSelectSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_items, null);
        init();
    }
    
    public void setIconResource(int icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageResource(icon);
    }
    
    public void setIconDrawable(Drawable icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageDrawable(icon);
    }
    
    public void setIconBitmap(Bitmap icon) {
        ((ImageView) dialogView.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
    	((ImageView) dialogView.findViewById(R.id.icon)).setImageBitmap(icon);
    }
    
    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
    }
    
    public void setOnSingleSelectCallBack(OnSingleSelectCallBack callback) {
         this.callback = callback;
    }
    
    public void addItem(int id, String text) {
        item = new HashMap<>();
        item.put(KEY_ITEM_ID, id);
        item.put(KEY_ITEM_TEXT, text);
        item.put(KEY_ITEM_CHECKED, false);
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
            } else {
                item = data.get(i);
                data.remove(i);
                item.put(KEY_ITEM_CHECKED, false);
                data.add(i, item);
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
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();
        
        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        
        SSingleSelectAdapter adapter = new SSingleSelectAdapter(data, callback, this);
        ((SRecyclerView) dialogView.findViewById(R.id.recycler))
            .setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
        ((SRecyclerView) dialogView.findViewById(R.id.recycler)).setAdapter(adapter);
    }
}
