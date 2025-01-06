/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/16/23, 10:13 PM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package smith.lib.alerts.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.*;
import java.util.*;
import smith.lib.alerts.dialog.adapters.SMenuAdapter;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallback;
import smith.lib.alerts.dialog.utils.MenuSDialogItem;

/**
 * The beautiful and most sorted class of SDialog lib.
 * this class create a list of items SDialogs that let the user choose the item he/she prefer.
 */
@SuppressWarnings({"unused"})
public class MenuSDialog extends SDialog {
    
    private List<MenuSDialogItem> iconsItemsList = new ArrayList<>();
    private OnItemClickCallback callback;
    private int maxHeight;

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public MenuSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_menu, null);
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
     * @param maxHeight an int value as maxHeight (it will automatically convert to dp);
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = utils.dp(maxHeight);
    }

    /**
     * Add functionality to when an item clicked.
     * @param callback A callback using {@link OnItemClickCallback}
     */
    public void setOnItemClickCallback(OnItemClickCallback callback) {
        this.callback = callback;
    }

    /**
     * Set the list of items to be displayed on MenuSDialog.
     * @param itemsList A list of items as {@link List} of {@link MenuSDialogItem}.
     */
    public void setItemsList(List<MenuSDialogItem> itemsList) {
        this.iconsItemsList = itemsList;
    }

    /**
     * Add a list of items to existing list to display on ItemsSDialog.
     * @param itemsList A list of items as {@link List} of {@link MenuSDialogItem}.
     */
    public void addItemsList(List<MenuSDialogItem> itemsList) {
        this.iconsItemsList.addAll(itemsList);
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

        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        SMenuAdapter adapter = new SMenuAdapter(iconsItemsList, callback, this);
        b.recycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        b.recycler.setAdapter(adapter);
        if (maxHeight != 0) b.recycler.setMaxHeight(maxHeight);
    }
}
