/*
 *
 *   Created by Dev. Smith (Hussein Shakir) on 9/16/23, 10:07 PM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 *   Last modified: 8/16/23, 3:39 PM
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *                     You may obtain a copy of the License at
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * /
 */

package smith.lib.alerts.dialog.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

/**
 * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
 */
public class MenuSDialogItem {

    private final Drawable icon;
    private final String title;

    /**
     * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
     * @param icon the icon of the item as {@link Drawable}.
     * @param title the title of the item as {@link String}.
     */
    public MenuSDialogItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    /**
     * Use this class to add new item to the list in {@link smith.lib.alerts.dialog.MenuSDialog}.
     * @param context current {@link Context} (or {@link Activity}).
     * @param icon the icon of the item as int {@link DrawableRes}.
     * @param title the title of the item as {@link String}.
     */
    public MenuSDialogItem(Context context, @DrawableRes int icon, String title) {
        this.icon = ContextCompat.getDrawable(context, icon);
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
