package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.*;
import java.util.*;
import smith.lib.alerts.dialog.adapters.SItemsAdapter;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;

/**
 * The beautiful and most sorted class of SDialog lib.
 * this class create a list of items SDialogs that let the user choose the item he/she prefer.
 */
public class ItemsSDialog extends SDialog {
    
    private List<String> data = new ArrayList<>();
    private OnItemClickCallBack callback;

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public ItemsSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_items, null);
        init();
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As Int resource (R.drawable.icon).
     */
    public void setIconResource(@DrawableRes int icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageResource(icon);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As {@link Drawable}.
     */
    public void setIconDrawable(Drawable icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageDrawable(icon);
    }

    /**
     * Set icon for the current SDialog.
     *
     * @param icon As {@link Bitmap}.
     */
    public void setIconBitmap(Bitmap icon) {
        b.icon.setVisibility(View.VISIBLE);
        b.icon.setImageBitmap(icon);
    }

    /**
     * Set title from string to current SDialog.
     *
     * @param title String title.
     */
    public void setTitle(String title) {
        b.title.setText(title);
    }

    /**
     * Set text from int resource to current SDialog.
     *
     * @param resTitle Int resource title (R.string.title).
     */
    public void setTitle(@StringRes int resTitle) {
        b.title.setText(resTitle);
    }

    /**
     * Set the list of items to display on ItemsSDialog.
     * @param dataList A list of items as {@link List<String>}
     */
    public void setItemsList(List<String> dataList) {
        data = dataList;
    }

    /**
     * Add functionality to when an item clicked.
     * @param callback A callback using {@link OnItemClickCallBack}
     */
    public void setOnItemClickCallBack(OnItemClickCallBack callback) {
        this.callback = callback;
    }

    /**
     * Declare displayed negative button text.
     * @param text String represent the text of negative button (e.g. "Cancel")
     */
    public void setNegativeButtonText(String text) {
    	b.holder.setVisibility(View.VISIBLE);
        b.positive.setText(text);
        b.positive.setOnClickListener(v->this.dismiss());
    }

    /**
     * Add an item to the list of ItemSDialog
     * @param item the item as {@link String}
     */
    public void addItem(String item) {
        data.add(item);
        update();
    }

    /**
     * Remove an item from the list of ItemSDialog
     * @param item the item as {@link String}
     */
    public void removeItem(String item) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(item)) data.remove(item);
        }
        update();
    }

    /**
     * Remove an item from the list of ItemSDialog
     * @param index the index of that item as {@link Integer}
     */
    public void removeItem(int index) {
        data.remove(index);
        update();
    }

    /**
     * @return Accent color of current SDialog showed as light theme or dark theme.
     */
    public int getAccentColor() {
        return accentColor;
    }

    /**
     * @return Title color of current SDialog showed as light theme or dark theme.
     */
    public int getTitleColor() {
        return titleColor;
    }

    /**
     * @return Background color of current SDialog showed as light theme or dark theme.
     */
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
