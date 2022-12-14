package smith.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.itsaky.androidide.logsender.LogSender;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import smith.lib.alerts.dialog.AlertSDialog;
import smith.lib.alerts.dialog.CustomSDialog;
import smith.lib.alerts.dialog.InputSDialog;
import smith.lib.alerts.dialog.ItemsSDialog;
import smith.lib.alerts.dialog.LoadingSDialog;
import smith.lib.alerts.dialog.MultiSelectSDialog;
import smith.lib.alerts.dialog.ProgressSDialog;
import smith.lib.alerts.dialog.SDialog;
import smith.lib.alerts.dialog.SingleSelectSDialog;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;

public class MainActivity extends AppCompatActivity {
    
    int p = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void multi(View v) {
        MultiSelectSDialog sdialog = new MultiSelectSDialog(this);
        sdialog.setAccentColor(SDialog.DEFAULT_COLOR);
        sdialog.setTheme(SDialog.DARK_THEME);
        sdialog.setTitle("Search Filters");
        sdialog.setCancelable(true);
        
        sdialog.addItem(1, "Images (jpg/jpeg/png)", true);
        sdialog.addItem(2, "GIF", false);
        sdialog.addItem(3, "Videos (mp4)", true);
        
        sdialog.setPositiveButton("Save & Search", (itemsList, idKey, textKey, checkedKey) -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < itemsList.size(); i++) {
                builder.append(itemsList.get(i).get(idKey));
                builder.append(" ");
                builder.append(itemsList.get(i).get(textKey));
                builder.append(" ");
                builder.append(itemsList.get(i).get(checkedKey));
            }
            Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
        });
        
        sdialog.show();
    }
    
    public void single(View v) {
        SingleSelectSDialog sdialog = new SingleSelectSDialog(this);
        sdialog.setAccentColor(SDialog.DEFAULT_COLOR);
        sdialog.setTheme(SDialog.DARK_THEME);
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
        sdialog.setTheme(SDialog.LIGHT_THEME);
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
        sdialog.setTheme(SDialog.LIGHT_THEME);
        sdialog.setTitle("Set Your Gender:");
        
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Bigender");
        genders.add("Prefer not to say");
        
        sdialog.setItemsList(genders);
        sdialog.setOnItemClickCallBack((object, itemValue, itemIndex) -> {
            Toast.makeText(this, object.toString() + (itemIndex+1) + itemValue, Toast.LENGTH_SHORT).show();
        });
        sdialog.addItem("Male to Female");
        sdialog.addItem("Female to Male");
        
        sdialog.removeItem("Prefer not to say");
        // sdialog.removeItem(2);
        
        sdialog.show();
    }
    
    public void input(View v) {
        InputSDialog sdialog = new InputSDialog(this);
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.LIGHT_THEME);
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
    
    public void alert(View v) {
        AlertSDialog sdialog = new AlertSDialog(this);
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
        sdialog.setTheme(SDialog.DARK_THEME);
        sdialog.setCancelable(true);
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show();
    }
    
    public void loading(View v) {
        LoadingSDialog sdialog = new LoadingSDialog(this);
        sdialog.setTitle("Loading SDialog!");
        sdialog.setText("Please wait a second...");
        sdialog.setAccentColor(0xFFAD97BE);
        sdialog.setTheme(SDialog.SYSTEM_THEME);
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show(6*1000);
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
}
