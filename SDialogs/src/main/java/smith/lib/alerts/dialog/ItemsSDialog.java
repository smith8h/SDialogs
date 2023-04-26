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
import androidx.recyclerview.widget.RecyclerView;

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
        if (theme == THEME_BY_SYSTEM) {
            if (nightModeON()) darkThemeColors();
            else lightThemeColors();
        } else if (theme == THEME_DARK) darkThemeColors();
        else if (theme == THEME_LIGHT) lightThemeColors();
        
        setBackgroundColor(dialogView.findViewById(R.id.main), backgroundColor);
        ((ImageView) dialogView.findViewById(R.id.icon)).setColorFilter(iconColor);
        ((TextView) dialogView.findViewById(R.id.title)).setTextColor(titleColor);
        
        SItemsAdapter adapter = new SItemsAdapter(data, callback, this);
        ((SRecyclerView) dialogView.findViewById(R.id.recycler))
            .setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        ((SRecyclerView) dialogView.findViewById(R.id.recycler)).setAdapter(adapter);
    }
}
