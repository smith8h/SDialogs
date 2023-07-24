package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.*;
import java.util.*;
import smith.lib.alerts.dialog.adapters.SItemsAdapter;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;

    public class ItemsSDialog extends SDialog {
    
    private List<String> data = new ArrayList<>();
    private OnItemClickCallBack callback;
    
    @SuppressLint("InflateParams")
    public ItemsSDialog(Context context) {
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
    
    public void setNegativeButtonText(String btnText) {
    	b.holder.setVisibility(View.VISIBLE);
        b.positive.setText(btnText);
        b.positive.setOnClickListener(v->this.dismiss());
    }
    
    public void addItem(String item) {
        data.add(item);
        update();
    }
    
    public void removeItem(String item) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(item)) data.remove(item);
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
    public void show(long duration) {
        update();
        super.show();
    }
    
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        
        SItemsAdapter adapter = new SItemsAdapter(data, callback, this);
        b.recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        b.recycler.setAdapter(adapter);
    }
}
