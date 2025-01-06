/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/17/23, 7:43 PM
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
import android.os.Handler;
import android.view.View;
import androidx.annotation.*;
import smith.lib.alerts.dialog.callbacks.OnBiometricAuthCallback;
import smith.lib.alerts.dialog.callbacks.OnClickCallback;
import smith.lib.alerts.dialog.callbacks.OnDismissCallback;
import smith.lib.alerts.dialog.utils.SBiometricAuth;

/**
 * The safest and most secured class of SDialog lib.
 * this class create a security SDialogs that alerts the user to input his fingerprint to access or proceed to something.
 */
@SuppressWarnings({"unused"})
public class BiometricSDialog extends SDialog {

    private OnBiometricAuthCallback callBack;
    private SBiometricAuth auth;
    private String text, errorText, successText;

    /**
     * Pass the current context you're using this sdialog from.
     * @param context Current context (or Activity).
     */
    @SuppressLint("InflateParams")
    public BiometricSDialog(Context context) {
        super.context = context;
        dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.sdialog_biometric, null);
        init();
    }

    /**
     * Declare displayed negative button text and its functionality.
     *
     * @param text a string represents the text of negative button (e.g. "<b>CANCEL</b>")
     */
    public void setNegativeButtonText(String text) {
        b.negative.setText(text);
        b.negative.setOnClickListener(v -> dismiss());
    }

    /**
     * Declare displayed positive button text and its functionality.
     *
     * @param text a string represents the text of positive button (e.g. "<b>Use Password</b>").
     * @param clickCallBack A click callback triggered when clicking positive button using {@link OnClickCallback}.
     */
    public void setPositiveButton(String text, OnClickCallback clickCallBack) {
        b.positive.setText(text);
        b.positive.setOnClickListener(v -> {
            clickCallBack.onClick();
            dismiss();
        });
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
     * Set text as {@link String} to current SDialog.
     *
     * @param text {@link String} text.
     */
    public void setText(String text) {
        this.text = text;
        b.fingerprintText.setText(this.text);
    }

    /**
     * Set text from int resource to current SDialog.
     *
     * @param text Int resource text (R.string.text).
     */
    public void setText(@StringRes int text) {
        this.text = context.getString(text);
        b.fingerprintText.setText(this.text);
    }

    /**
     * Set the error message text as {@link String} to current SDialog.
     *
     * @param errorText {@link String} text.
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * Set the error message text from int resource to current SDialog.
     *
     * @param errorText Int resource text (R.string.text).
     */
    public void setErrorText(@StringRes int errorText) {
        this.errorText = context.getString(errorText);
    }

    /**
     * Set the success message text as {@link String} to current SDialog.
     *
     * @param successText {@link String} text.
     */
    public void setSuccessText(String successText) {
        this.successText = successText;
    }

    /**
     * Set the success message text from int resource to current SDialog.
     *
     * @param successText Int resource text ({@code R.string.text}).
     */
    public void setSuccessText(@StringRes int successText) {
        this.successText = context.getString(successText);
    }

    /**
     * Set the max tries to authenticate using biometric hardware, default is 3 tries.
     * @param maxFailureCount The max retries.
     */
    public void setMaxFailureCount(int maxFailureCount) {
        if (auth != null) auth.setMaxFailureCount(maxFailureCount);
    }

    /**
     * Set the callback to interact with biometric authentication response when needed, using
     * {@link OnBiometricAuthCallback}.
     * @param callBack the callback using {@link OnBiometricAuthCallback}.
     */
    public void setOnBiometricAuthCallBack(OnBiometricAuthCallback callBack) {
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

    /**
     * @return Text color of AlertSDialog as displayed as Dark or Light Theme.
     */
    public int getTextColor() {
        return textColor;
    }

    @Override
    public void setOnDismissCallBack(OnDismissCallback callback) {
        this.dismissCallback = callback;
        alertdialog.setOnDismissListener(dialog -> {
            if (auth != null) auth.cancelSignal();
            dismissCallback.onDismiss();
        });
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

    @SuppressLint("SetTextI18n")
    private void update() {
        updateTheme();
        utils.backgroundColor(b.main, backgroundColor);
        b.icon.setColorFilter(iconColor);
        b.title.setTextColor(titleColor);
        b.fingerprintText.setTextColor(textColor);
        b.negative.setTextColor(accentColor);

        if (errorText.isEmpty()) errorText = context.getString(R.string.sdialog_biometric_not_recognized);
        if (successText.isEmpty()) successText = context.getString(R.string.sdialog_biometric_success);

        if (SBiometricAuth.hasBiometrics(context)) {
            updateIcon(iconColor, iconBackground);
            initAuth();
            setCancelable(false);
        } else {
            updateIcon(0xFFEB6D64, 0x47FF7D5F);
            b.fingerprintText.setText(
                    "No biometric hardware found, or no biometric authentication registered on this device."
            );
            b.holder.setVisibility(View.GONE);
            setCancelable(true);
        }

        if (dismissCallback == null) {
            alertdialog.setOnDismissListener(dialogInterface -> {
                if (auth != null) auth.cancelSignal();
            });
        }
    }

    private void initAuth() {
        auth = new SBiometricAuth(context);
        auth.setCallback(new OnBiometricAuthCallback() {
            @Override
            public void onFailure() {
                updateIcon(0xFFEB6D64, 0x47FF7D5F);
                b.fingerprintText.setText(errorText);

                new Handler().postDelayed(() -> {
                    updateIcon(iconColor, iconBackground);
                    b.fingerprintText.setText(text);
                    callBack.onFailure();
                }, 1000);
            }

            @Override
            public void onError() {
                updateIcon(0xFFEB6D64, 0x47FF7D5F);
                b.fingerprintText.setText(errorText);

                new Handler().postDelayed(() -> {
                    callBack.onError();
                    dismiss();
                }, 1600);
            }

            @Override
            public void onSuccess() {
                updateIcon(0xFF53A74C, 0x47397434);
                b.fingerprintText.setText(successText);

                new Handler().postDelayed(() ->{
                    callBack.onSuccess();
                    dismiss();
                }, 1600);
            }
        });
    }

    private void updateIcon(int color, int background) {
        b.fingerprint.setColorFilter(color);
        utils.backgroundColor(b.fingerprint, background);
    }
}
