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

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import smith.lib.alerts.dialog.callbacks.OnBindCustomViewCallBack;

/**
 * The fully customizable class of SDialog lib.
 * this class create an alert SDialogs that is full depends on you as you set its layout and design and functionality.
 */
@SuppressWarnings({"unused"})
public class CustomSDialog extends SDialog {

    private final View view;

    /**
     * Create new instance of fully customizable dialog.
     * @param context Current context (or Activity).
     * @param view the custom view as  {@link View}.
     */
    public CustomSDialog(Context context, View view) {
        this.context = context;
        this.view = view;
        dialogView = this.view;
        init();
    }

    /**
     * Create new instance of fully customizable dialog.
     * @param context Current context (or Activity).
     * @param layout the custom layout resource as  {@link LayoutRes} res.
     */
    public CustomSDialog(Context context, @LayoutRes int layout) {
        this.context = context;
        this.view = ((Activity) context).getLayoutInflater().inflate(layout, null);
        dialogView = this.view;
        init();
    }

    /**
     * Set the callback to interact with the dialog onBindingView of the SDialog.
     * @param callback On bind view callback using {@link OnBindCustomViewCallBack}
     */
    public void setOnBindViewCallBack(@NonNull OnBindCustomViewCallBack callback) {
        callback.onBindCustomView(view);
    }

    /**
     * This setter won't work on custom type of SDialog.
     */
    @Deprecated
    @Override
    public void setAccentColor(int color) {
    }

    /**
     * This setter won't work on custom type of SDialog.
     */
    @Deprecated
    @Override
    public void setAccentColor(String color) {
    }

    /**
     * This setter won't work on custom type of SDialog.
     */
    @Deprecated
    @Override
    public void setTheme(int theme) {
    }
}
