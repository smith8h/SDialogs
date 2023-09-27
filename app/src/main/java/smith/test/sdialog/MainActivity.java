/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/17/23, 7:37 PM
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

package smith.test.sdialog;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import smith.lib.alerts.dialog.AlertSDialog;
import smith.lib.alerts.dialog.CustomSDialog;
import smith.lib.alerts.dialog.FeedbackSDialog;
import smith.lib.alerts.dialog.BiometricSDialog;
import smith.lib.alerts.dialog.InputSDialog;
import smith.lib.alerts.dialog.LoadingSDialog;
import smith.lib.alerts.dialog.MenuSDialog;
import smith.lib.alerts.dialog.MultiSelectSDialog;
import smith.lib.alerts.dialog.ProgressSDialog;
import smith.lib.alerts.dialog.SDialog;
import smith.lib.alerts.dialog.SingleSelectSDialog;
import smith.lib.alerts.dialog.SliderSDialog;
import smith.lib.alerts.dialog.callbacks.OnBiometricAuthCallBack;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;
import smith.lib.alerts.dialog.utils.MenuSDialogItem;

public class MainActivity extends AppCompatActivity {
    int p = 0;
    Timer timer = new Timer();
    TimerTask task = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new TestFragment());
        transaction.commit();
    }

    public void alert(View v) {
        AlertSDialog sdialog = new AlertSDialog(this);
        sdialog.setIconDrawable(ContextCompat.getDrawable(this, R.drawable.ok_img));
        sdialog.setTitle("Title Of SDialog");
        sdialog.setText(getString(R.string.dummy_text));
        sdialog.setPositiveButton("OK", () -> {
            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            sdialog.dismiss();
        });
        sdialog.setNegativeButton("Cancel", () -> {
            Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
            sdialog.dismiss();
        });
        sdialog.setNeutralButton("Hide", () -> {
            Toast.makeText(this, "hide", Toast.LENGTH_SHORT).show();
            sdialog.dismiss();
        });
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setCancelable(true);
        sdialog.setMaxHeight(150);
        sdialog.setOnDismissCallBack(() -> Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show());
        sdialog.show();
    }

    public void custom(View v) {
        CustomSDialog sdialog = new CustomSDialog(this, R.layout.activity_main);
        sdialog.setCancelable(true);
        sdialog.setOnDismissCallBack(() -> Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show());
        sdialog.setOnBindViewCallBack(customView -> {
            // dialog logic here
        });
        sdialog.show();
    }

    public void feedback(View view) {
        FeedbackSDialog sDialog = new FeedbackSDialog(this);
        sDialog.setTitle("Feedback SDialog!");
        sDialog.setText("Your feedback is valuable, please rate our work.");
        sDialog.setAccentColor(0xFFAD97BE);
        sDialog.setIconResource(R.drawable.ok_img);
        sDialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sDialog.setInputFieldHint("Feedback...");
        sDialog.setSubmitFeedBackButton("Submit Feedback", (isLiked, feedbackText) ->
                Toast.makeText(this, "isLiked " + isLiked, Toast.LENGTH_SHORT).show()
        );
        sDialog.show();
    }

    public void finger(View view) {
        BiometricSDialog sDialog = new BiometricSDialog(this);
        sDialog.setTheme(SDialog.THEME_LIGHT);
        sDialog.setAccentColor(SDialog.COLOR_DEFAULT);
        sDialog.setIconResource(R.drawable.lock);
        sDialog.setTitle("Fingerprint Confirmation");
        sDialog.setText("Confirm your fingerprint to proceed further to the next payment step.");
        sDialog.setNegativeButtonText("Cancel");
        sDialog.setPositiveButton("Use Password", () -> {});
        sDialog.setMaxFailureCount(3);
        sDialog.setErrorText("Failed to recognise fingerprint. Try again!");
        sDialog.setSuccessText("Fingerprint recognised!");
        sDialog.setOnBiometricAuthCallBack(new OnBiometricAuthCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Success read fingerprint", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "Fail to read fingerprint", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Error reading fingerprint", Toast.LENGTH_SHORT).show();
            }
        });
        sDialog.show();
    }

    public void input(View v) {
        InputSDialog sdialog = new InputSDialog(this);
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setCancelable(false);
        sdialog.setIconResource(R.drawable.ok_img);
        sdialog.setTitle("Input Your Name");
        sdialog.setText("please write your full name in no more than 20 characters.");
        sdialog.setInputFieldHint("Your Name");
        sdialog.setPositiveButtonAction("Save", inputText -> Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show());
        sdialog.setNegativeButtonText("Close");
        sdialog.setOnDismissCallBack(() -> Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show());
        sdialog.show();
    }

    public void items(View v) {
        MenuSDialog sdialog = new MenuSDialog(this);
        sdialog.setAccentColor(SDialog.COLOR_DEFAULT);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setMaxHeight(360);
        sdialog.setTitle("Sequence Options");

        List<MenuSDialogItem> items = new ArrayList<>();
        items.add(new MenuSDialogItem(ContextCompat.getDrawable(this, R.drawable.ok_img),
                getString(R.string.app_name)));
        items.add(new MenuSDialogItem(this, R.drawable.ok_img, getString(R.string.app_name)));

        sdialog.addItemsList(items);
        sdialog.addItem(R.drawable.ok_img, getString(R.string.app_name));

        sdialog.setOnItemClickCallBack((index, item) -> Toast.makeText(this, index + " " + (item.getTitle()), Toast.LENGTH_SHORT).show());
//        sdialog.removeItem(2);
        sdialog.show();
    }

    public void loading(View v) {
        LoadingSDialog sdialog = new LoadingSDialog(this);
        sdialog.setTitle("Loading SDialog!");
        sdialog.setText("Please wait a second...");
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setOnDismissCallBack(() -> Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show());
        sdialog.show(6 * 1000);
    }

    public void multi(View v) {
        MultiSelectSDialog sdialog = new MultiSelectSDialog(this);
        sdialog.setAccentColor(SDialog.COLOR_DEFAULT);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setTitle("Search Filters");
        sdialog.setCancelable(true);
        sdialog.setMaxHeight(150);

        sdialog.addItem(1, "Images (jpg/jpeg/png)", true);
        sdialog.addItem(2, "GIF", false);
        sdialog.addItem(3, "Videos (mp4)", true);

        sdialog.setPositiveButton("Save & Search", (itemsList) -> {
            StringBuilder builder = new StringBuilder();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemsList.forEach(item -> {
                    builder.append(item.get(SDialog.KEY_ITEM_ID));
                    builder.append(" ");
                    builder.append(item.get(SDialog.KEY_ITEM_TEXT));
                    builder.append(" ");
                    builder.append(item.get(SDialog.KEY_ITEM_CHECKED));
                    builder.append("\n");
                });
            }
            Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
        });

        sdialog.show();
    }

    public void progress(View v) {
        ProgressSDialog sdialog = new ProgressSDialog(this);
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setIconResource(R.drawable.ok_img);
        sdialog.setMax(200);
        sdialog.setMin(0);
        sdialog.setTitle("Downloading Files");
        sdialog.setText("Gathering Resources...");
        sdialog.setOnProgressCallBack(new OnProgressCallBack() {
            @Override
            public void onProgress(int progress, int percent) {
                if (percent > 0 && percent <= 20) sdialog.setText("Gathering Resources...");
                if (percent > 20 && percent <= 50) sdialog.setText("Downloading Resources...");
                if (percent > 50 && percent <= 80) sdialog.setText("Extracting Resources...");
                if (percent > 80 && percent <= 98) sdialog.setText("Installing Resources...");
                if (percent >= 99) sdialog.setText("Resources Installed.");
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Download Complete!", Toast.LENGTH_SHORT).show();
            }
        });
        sdialog.setNegativeButtonAction("Cancel Process", () -> {
            timer.cancel();
            p = 0;
        });
        sdialog.show();

        task = new TimerTask() { @Override public void run() {
            runOnUiThread(() -> {
                p++;
                sdialog.setProgress(p);
                if (sdialog.getProgress() == sdialog.getMax()) {
                    timer.cancel();
                    p = 0;
                }
            });
        }};
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    public void single(View v) {
        SingleSelectSDialog sdialog = new SingleSelectSDialog(this);
        sdialog.setAccentColor(SDialog.COLOR_DEFAULT);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setTitle("Notifications Sounds");
        sdialog.setCancelable(true);
        sdialog.setMaxHeight(150);

        sdialog.addItem(1, "ON");
        sdialog.addItem(2, "OFF");
        sdialog.addItem(3, "ON (SILENT)");

        sdialog.setCheckedItem(2);

        sdialog.setOnSingleSelectCallBack((itemId, itemText) -> Toast.makeText(this, itemId + " " + itemText, Toast.LENGTH_SHORT).show());

        sdialog.show();
    }

    public void slider(View v) {
    	SliderSDialog d = new SliderSDialog(this);
        d.setAccentColor(SDialog.COLOR_DEFAULT);
        d.setCancelable(false);
        d.setIconResource(R.drawable.ok_img);
        d.setMax(120);
        d.setMin(0);
        d.setStepBy(3);
        d.setNegativeButtonText("Cancel");
        d.setPositiveButtonAction("Confirm", value -> Toast.makeText(this, "" + value, Toast.LENGTH_SHORT).show());
        d.setTheme(SDialog.THEME_DARK);
        d.setTitle("Confirm Your Age");
        d.setText("Select the distance between you and the people you want to met.");
        d.show();
    }
}
