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
import smith.lib.alerts.dialog.ProgressSDialog;
import smith.lib.alerts.dialog.SDialog;
import smith.lib.alerts.dialog.callbacks.OnProgressCallBack;

public class MainActivity extends AppCompatActivity {
    
    int p = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            @Override public void onProgress(int progress) {
                if (progress > 0 && progress <= 40) sdialog.setText("Gethering Resources...");
                if (progress > 40 && progress <= 100) sdialog.setText("Downloading Resources...");
                if (progress > 100 && progress <= 160) sdialog.setText("Extracting Resources...");
                if (progress > 160 && progress <= 198) sdialog.setText("Installing Resources...");
                if (progress >= 199) sdialog.setText("Resources Installed.");
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
        
        sdialog.setDataList(genders, (object, itemValue, itemIndex) -> {
            Toast.makeText(this, object.toString() + (itemIndex+1) + itemValue, Toast.LENGTH_SHORT).show();
        });
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
