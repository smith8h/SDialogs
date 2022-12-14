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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import smith.lib.alerts.dialog.adapters.SItemsAdapter;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;
import smith.lib.views.recyclerview.SRecyclerView;

public class ItemsSDialog extends SDialog {
    
    private List<String> data = new ArrayList<>();
    private OnItemClickCallBack callback;
    
    public ItemsSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_items, null);
        init();
    }
    
    public void setTitle(String title) {
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
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
    
    public void setItemsList(List<String> dataList) {
        data = dataList;
    }
    
    public void setOnItemClickCallBack(OnItemClickCallBack callback) {
        this.callback = callback;
    }
    
    public void addItem(String item) {
        data.add(item);
        update();
    }
    
    public void removeItem(String item) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(item)) data.remove(i);
        }
        update();
    }
    
    public void removeItem(int index) {
        data.remove(index);
        update();
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
        
        SItemsAdapter adapter = new SItemsAdapter(data, callback, this);
        ((SRecyclerView) dialogView.findViewById(R.id.recycler))
            .setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
        ((SRecyclerView) dialogView.findViewById(R.id.recycler)).setAdapter(adapter);
    }
}
