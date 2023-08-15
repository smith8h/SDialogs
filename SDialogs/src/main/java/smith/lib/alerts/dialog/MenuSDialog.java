package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.*;
import java.util.*;
import smith.lib.alerts.dialog.adapters.SMenuAdapter;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack;
import smith.lib.alerts.dialog.utils.MenuSDialogItem;

/**
 * The beautiful and most sorted class of SDialog lib.
 * this class create a list of items SDialogs that let the user choose the item he/she prefer.
 */
@SuppressWarnings({"unused"})
public class MenuSDialog extends SDialog {
    
    private final List<MenuSDialogItem> iconsItemsList = new ArrayList<>();
    private OnItemClickCallBack callback;
    private int maxHeight;

    /**
     * Pass the current context you using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public MenuSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_menu, null);
        init();
    }

    /**
     * Set the list of items to display on ItemsSDialog.
     * @param iconsItemsList A list of items as {@link List} of {@link MenuSDialogItem}
     */
    public void setItemsList(List<MenuSDialogItem> iconsItemsList) {
        this.iconsItemsList.addAll(iconsItemsList);
    }

    /**
     * Set a max height to fix screen overriding content.
     * @param maxHeight an int value as maxHeight (it will automatically converted to dp);
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = (int) utils.dp(maxHeight);
    }

    /**
     * Add functionality to when an item clicked.
     * @param callback A callback using {@link OnItemClickCallBack}
     */
    public void setOnItemClickCallBack(OnItemClickCallBack callback) {
        this.callback = callback;
    }

    /**
     * Add an item to the list of MenuSDialog
     *
     * @param drawable item icon as {@link Drawable}.
     * @param title item title as {@link String}.
     */
    public void addItem(Drawable drawable, String title) {
        iconsItemsList.add(new MenuSDialogItem(drawable, title));
    }

    /**
     * Add an item to the list of MenuSDialog
     *
     * @param resource item icon as {@link Integer}.
     * @param title item title as {@link String}.
     */
    public void addItem(@DrawableRes int resource, String title) {
        iconsItemsList.add(new MenuSDialogItem(ContextCompat.getDrawable(context, resource), title));
        update();
    }

    /**
     * Remove an item from the list of MenuSDialog
     * @param index the index of that item as {@link Integer}
     */
    public void removeItem(int index) {
        iconsItemsList.remove(index);
        update();
    }

    /**
     * @return Accent color of current SDialog showed as light theme or dark theme.
     */
    public int getAccentColor() {
        return accentColor;
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
        
        SMenuAdapter adapter = new SMenuAdapter(iconsItemsList, callback, this);
        b.recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        b.recycler.setAdapter(adapter);
        if (maxHeight != 0) b.recycler.setMaxHeight(maxHeight);
    }
}
