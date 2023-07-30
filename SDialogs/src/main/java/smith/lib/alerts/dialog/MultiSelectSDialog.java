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

import smith.lib.alerts.dialog.adapters.SMultiSelectAdapter;
import smith.lib.alerts.dialog.callbacks.OnMultiSelectCallBack;

/**
 * The beautiful and most functional class of SDialog lib.
 * this class create an items with checkboxes SDialogs that allow the user to select multiple items.
 */
public class MultiSelectSDialog extends SDialog {
    
    private SMultiSelectAdapter adapter;
    private final List<Map<String, Object>> data = new ArrayList<>();
    private Map<String, Object> item = new HashMap<>();

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public MultiSelectSDialog(Context context) {
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
     * Set the text to be displayed on positive button.
     * @param text as {@link String} text.
     * @param callback a callback using {@link OnMultiSelectCallBack}
     */
    public void setPositiveButton(String text, OnMultiSelectCallBack callback) {
         b.holder.setVisibility(View.VISIBLE);
         b.positive.setText(text);
         b.positive.setOnClickListener(v-> {
             if (adapter != null) callback.onMultiSelect(adapter.getCheckedItems());
             dismiss();
         });
    }

    /**
     * Add new items to the list of items in the SDialog.
     * @param id the id of that item as {@link Integer}.
     * @param text the text of that item as {@link String}.
     * @param checked The flag that set the item as checked by default or not as {@link Boolean}.
     */
    public void addItem(int id, String text, boolean checked) {
        item = new HashMap<>();
        item.put(KEY_ITEM_ID, id);
        item.put(KEY_ITEM_TEXT, text);
        item.put(KEY_ITEM_CHECKED, checked);
        data.add(item);
        update();
    }

    /**
     * Set the checked item by default.
     * @param id the id of that item as {@link Integer}.
     */
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

    /**
     * Remove an exist item from the list.
     * @param itemText the item required by its text.
     */
    public void removeItem(String itemText) {
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> item = data.get(i);
            if (Objects.requireNonNull(item.get("text")).toString().equals(itemText))
                data.remove(item);
        }
        update();
    }

    /**
     * Remove an exist item from the list.
     * @param itemIndex the item required by its id.
     */
    public void removeItem(int itemIndex) {
        data.remove(itemIndex);
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

    /**
     * @return Text color of AlertSDialog as displayed as Dark or Light Theme.
     */
    public int getTextColor() {
        return textColor;
    }

    /**
     * Get the list of items that been added to the SDialog.
     * @return a list of map of strings as keys and objects as values.
     */
    public List<Map<String, Object>> getItemsList() {
        return data;
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
        b.positive.setTextColor(accentColor);
        
        adapter = new SMultiSelectAdapter(data, this);
        b.recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        b.recycler.setAdapter(adapter);
    }
}
