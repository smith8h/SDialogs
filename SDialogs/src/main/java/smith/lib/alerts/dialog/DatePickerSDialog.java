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
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.*;
import androidx.recyclerview.widget.LinearLayoutManager;

import smith.lib.alerts.dialog.adapters.SPickerAdapter;
import smith.lib.alerts.dialog.callbacks.*;
import smith.lib.alerts.dialog.utils.*;

/**
 * The beautiful class of SDialog lib.
 * this class create a date picker with beautiful design and user experience.
 * @since 5.0
 */
@SuppressWarnings({"unused"})
public class DatePickerSDialog extends SDialog {

    private OnDatePickedCallBack callBack;
    private SDateFormat.DAYS daysFormat;
    private SDateFormat.MONTHS monthsFormat;

    @SuppressLint("InflateParams")
    public DatePickerSDialog(Context context) {
        this.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_picker, null);
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
     * Declare displayed positive button text and its functionality.
     *
     * @param text     String represent the text of positive button (e.g. "Pick")
     * @param callback OnPositiveButtonClick callback using {@link OnClickCallBack}
     */
    public void setPositiveButton(String text, OnDatePickedCallBack callback) {
        b.positive.setText(text);
        b.positive.setOnClickListener(v -> {
            // todo : implement callback
            // callback.onDatePicked(Calendar.);
            dismiss();
        });
    }

    /**
     * Set the titles above each of the date pickers to your language titles as {@link String}
     * @param forDays The title for the days' picker.
     * @param forMonths The title for the months' picker.
     * @param forYears The title for the years' picker.
     */
    public void setPickerTitles(String forDays, String forMonths, String forYears) {
        b.dayTitle.setText(forDays);
        b.monthTitle.setText(forMonths);
        b.yearTitle.setText(forYears);
    }

    /**
     *
     * @param daysFormat a
     * @param monthsFormat a
     */
    public void setDaysAndMonthsFormat(@NonNull SDateFormat.DAYS daysFormat, SDateFormat.MONTHS monthsFormat) {
        this.daysFormat = daysFormat;
        this.monthsFormat = monthsFormat;
    }

    public void setOnDatePickedCallBack(OnDatePickedCallBack callBack) {
        this.callBack = callBack;
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
        super.show(duration);
    }

    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.dayTitle.setTextColor(accentColor);
        b.monthTitle.setTextColor(accentColor);
        b.yearTitle.setTextColor(accentColor);
        b.positive.setTextColor(accentColor);

        b.dayRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        b.monthRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        b.yearRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        if (daysFormat == SDateFormat.DAYS.D) {
            b.dayRV.setAdapter(new SPickerAdapter(SDialogUtils.getDays1Chars(), textColor));
        } else {
            b.dayRV.setAdapter(new SPickerAdapter(SDialogUtils.getDays2Chars(), textColor));
        }

        switch (monthsFormat) {
            case M -> b.monthRV.setAdapter(new SPickerAdapter(SDialogUtils.getMonths1Chars(), textColor));
            case MM -> b.monthRV.setAdapter(new SPickerAdapter(SDialogUtils.getMonths2Chars(), textColor));
            case MMM -> b.monthRV.setAdapter(new SPickerAdapter(SDialogUtils.getMonths3Chars(), textColor));
            default -> b.monthRV.setAdapter(new SPickerAdapter(SDialogUtils.getMonthsFullChars(), textColor));
        }

        b.yearRV.setAdapter(new SPickerAdapter(SDialogUtils.getYears(), textColor));

        SDialogUtils.SwipeSpinner.bindRecyclerView(b.dayRV);
        SDialogUtils.SwipeSpinner.bindRecyclerView(b.monthRV);
        SDialogUtils.SwipeSpinner.bindRecyclerView(b.yearRV);
    }
}
