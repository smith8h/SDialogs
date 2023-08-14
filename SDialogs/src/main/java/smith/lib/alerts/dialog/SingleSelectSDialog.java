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
import smith.lib.alerts.dialog.adapters.SSingleSelectAdapter;
import smith.lib.alerts.dialog.callbacks.OnSingleSelectCallBack;

/**
 * The beautiful and stunning class of SDialog lib.
 * this class create an alert SDialogs that alerts the user what going on.
 */
@SuppressWarnings({"all"})
public class SingleSelectSDialog extends SDialog {

    private final List<Map<String, Object>> data = new ArrayList<>();
    private Map<String, Object> item = new HashMap<>();
    private OnSingleSelectCallBack callback;
    private int maxHeight, maxWidth;

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public SingleSelectSDialog(Context context) {
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
     * Set a max height to fix screen overriding content.
     * @param maxHeight an int value as maxHeight (it will automatically converted to dp);
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = (int) utils.dp(maxHeight);
    }

    /**
     * Set a max width to fix screen overriding content.
     * @param maxWidth an int value as maxWidth (it will automatically converted to dp);
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = (int) utils.dp(maxWidth);
    }

    /**
     * Set a functional interface to get the chosen items when selected.
     * @param callback a callback using {@link OnSingleSelectCallBack}.
     */
    public void setOnSingleSelectCallBack(OnSingleSelectCallBack callback) {
         this.callback = callback;
    }

    /**
     * Add new item to the list.
     * @param id an int id represent the id of that item.
     * @param text a string text for the item.
     */
    public void addItem(int id, String text) {
        item = new HashMap<>();
        item.put(KEY_ITEM_ID, id);
        item.put(KEY_ITEM_TEXT, text);
        item.put(KEY_ITEM_CHECKED, false);
        data.add(item);
        update();
    }

    /**
     * Set the checked item by its id.
     * @param id an int value as id.
     */
    public void setCheckedItem(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (Objects.equals(data.get(i).get(KEY_ITEM_ID), id)) {
                item = data.get(i);
                data.remove(item);
                item.put(KEY_ITEM_CHECKED, true);
                data.add(i, item);
            } else {
                item = data.get(i);
                data.remove(item);
                item.put(KEY_ITEM_CHECKED, false);
                data.add(i, item);
            }
        }
        update();
    }

    /**
     * Remove an existing item from the list.
     * @param itemText a string represent the text of that item to be removed.
     */
    public void removeItem(String itemText) {
        for (int i = 0; i < data.size(); i++) {
            if (Objects.requireNonNull(data.get(i).get("text")).toString().equals(itemText)) data.remove(i);
        }
        update();
    }

    /**
     * Remove an existing item from the list.
     * @param itemIndex an int represent the id of that item to be removed.
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
     * Get the items added to the SDialog as list.
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
        
        SSingleSelectAdapter adapter = new SSingleSelectAdapter(data, callback, this);
        b.recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        b.recycler.setAdapter(adapter);
        if (maxHeight != 0) b.recycler.setMaxHeight(maxHeight);
        if (maxWidth != 0) b.recycler.setMaxWidth(maxWidth);
    }
}
