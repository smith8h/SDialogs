/*
 * Created by Dev. Smith (Hussein Shakir) on 9/28/23, 2:19 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/28/23, 2:19 PM
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
import android.view.View;

import smith.lib.alerts.dialog.callbacks.OnClickCallBack;

/**
 * The beautiful class of SDialog lib.
 * this class create a date picker with beautiful design and user experience.
 */
@SuppressWarnings({"unused"})
public class DatePickerSDialog extends SDialog {

    @SuppressLint("InflateParams")
    public DatePickerSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_picker, null);
        init();
    }

    /**
     * Declare displayed positive button text and its functionality.
     *
     * @param text     String represent the text of positive button (e.g. "Pick")
     * @param callback OnPositiveButtonClick callback using {@link OnClickCallBack}
     */
    public void setPositiveButton(String text, OnClickCallBack callback) {
        b.positive.setText(text);
        b.positive.setOnClickListener(v -> {
            callback.onClick();
            dismiss();
        });
    }
}
