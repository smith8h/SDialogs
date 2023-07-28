package smith.test.sdialog;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.*;
import smith.lib.alerts.dialog.*;
import smith.lib.alerts.dialog.callbacks.OnDrawPatternCallBack;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;

public class MainActivity extends AppCompatActivity {
    int p = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show();
    }

    public void custom(View v) {
        CustomSDialog sdialog = new CustomSDialog(this);
        sdialog.setView(R.layout.activity_main, customView -> {

        });
        sdialog.setCancelable(true);
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show();
    }

    public void feedback(View view) {
        FeedbackSDialog sDialog = new FeedbackSDialog(this);
        sDialog.setTitle("Feedback SDialog!");
        sDialog.setText("Your feedback is valuable, please rate our work.");
        sDialog.setAccentColor(0xFFAD97BE);
        sDialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sDialog.setOnFeedbackSubmitCallBack(isLiked ->
                Toast.makeText(this, "isLiked " + isLiked, Toast.LENGTH_SHORT).show()
        );
        sDialog.show();
    }

    public void input(View v) {
        InputSDialog sdialog = new InputSDialog(this);
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setCancelable(false);
        sdialog.setTitle("Input Your Name");
        sdialog.setText("please write your full name in no more than 20 characters.");
        sdialog.setInputFieldHint("Your Name");
        sdialog.setPositiveButtonAction("Save", inputText -> {
            Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show();
        });
        sdialog.setNegativeButtonText("Close");
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show();
    }

    public void pattern(View v) {
    	PatternSDialog d = new PatternSDialog(this);
        d.setAccentColor(SDialog.COLOR_DEFAULT);
        d.setIconResource(R.drawable.ok_img);
        d.setOnDrawPatternCallBack(new OnDrawPatternCallBack() {
            @Override
            public void onStartDrawing() {
                Toast.makeText(MainActivity.this, "Pattern drawing started!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCompleteDrawing(@NonNull String pattern) {
                Toast.makeText(MainActivity.this, "Pattern drawing completed, pattern is: " + pattern, Toast.LENGTH_SHORT).show();
                d.setPatternMode(SDialog.PATTERN_MODE_CORRECT);
                d.dismiss(500);
            }
            @Override
            public void onClearDrawing() {
                Toast.makeText(MainActivity.this, "Pattern drawing cleared!", Toast.LENGTH_SHORT).show();
            }
        });
        d.setTheme(SDialog.THEME_BY_SYSTEM);
        d.setTitle("Draw Pattern To Unlock");
        d.show();
        
    }
    
    public void seek(View v) {
    	SliderSDialog d = new SliderSDialog(this);
        d.setAccentColor(SDialog.COLOR_DEFAULT);
        d.setCancelable(false);
        d.setIconResource(R.drawable.ok_img);
        d.setMax(70f);
        d.setMin(10f);
        //d.setStepBy(1f);
        d.setNegativeButtonText("Cancel");
        d.setPositiveButtonAction("Confirm", value -> {
            Toast.makeText(this, "" + value, Toast.LENGTH_SHORT).show();
        });
        d.setTheme(SDialog.THEME_DARK);
        d.setTitle("Confirm Your Age");
        //d.setText("In order to use our app, you must confirm your age is over 13!");
        d.show();
    }
    
    public void multi(View v) {
        MultiSelectSDialog sdialog = new MultiSelectSDialog(this);
        sdialog.setAccentColor(SDialog.COLOR_DEFAULT);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setTitle("Search Filters");
        sdialog.setCancelable(true);
        
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
    
    public void single(View v) {
        SingleSelectSDialog sdialog = new SingleSelectSDialog(this);
        sdialog.setAccentColor(SDialog.COLOR_DEFAULT);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setTitle("Notifications Sounds");
        sdialog.setCancelable(true);
        
        sdialog.addItem(1, "ON");
        sdialog.addItem(2, "OFF");
        sdialog.addItem(3, "ON (SILENT)");
        
        sdialog.setCheckedItem(2);
        
        sdialog.setOnSingleSelectCallBack((itemId, itemText) -> {
            Toast.makeText(this, itemId + " " + itemText, Toast.LENGTH_SHORT).show();
        });
        
        sdialog.show();
    }
    
    public void progress(View v) {
        ProgressSDialog sdialog = new ProgressSDialog(this);
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setMax(200);
        sdialog.setMin(0);
        sdialog.setTitle("Downloading Files");
        sdialog.setText("Gethering Resources...");
        sdialog.setOnProgressCallBack(new OnProgressCallBack() {
            @Override public void onProgress(int progress, int percent) {
                if (percent > 0 && percent <= 20) sdialog.setText("Gethering Resources...");
                if (percent > 20 && percent <= 50) sdialog.setText("Downloading Resources...");
                if (percent > 50 && percent <= 80) sdialog.setText("Extracting Resources...");
                if (percent > 80 && percent <= 98) sdialog.setText("Installing Resources...");
                if (percent >= 99) sdialog.setText("Resources Installed.");
            }
            
            @Override public void onFinish() {
                Toast.makeText(MainActivity.this, "Download Complete!", Toast.LENGTH_SHORT).show();
            }
        });
        sdialog.show();
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() { @Override public void run() {
            runOnUiThread(new Runnable() { @Override public void run() {
                p++;
                sdialog.setProgress(p);
                if (sdialog.getProgress() == sdialog.getMax()) {
                    timer.cancel();
                    p = 0;
                }
            }});
        }};
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    
    public void items(View v) {
        ItemsSDialog sdialog = new ItemsSDialog(this);
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setCancelable(false);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setTitle("Set Your Gender:");
        
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Bigender");
        genders.add("Prefer not to say");
        
        sdialog.setItemsList(genders);
        sdialog.setOnItemClickCallBack((itemValue, itemIndex) -> {
            Toast.makeText(this, itemValue + (itemIndex+1), Toast.LENGTH_SHORT).show();
        });
        sdialog.addItem("Male to Female");
        sdialog.addItem("Female to Male");
        
        sdialog.removeItem("Prefer not to say");
        // sdialog.removeItem(2);
        
        sdialog.show();
    }
    

    
    public void loading(View v) {
        LoadingSDialog sdialog = new LoadingSDialog(this);
        sdialog.setTitle("Loading SDialog!");
        sdialog.setText("Please wait a second...");
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.THEME_BY_SYSTEM);
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show(6*1000);
    }


}
