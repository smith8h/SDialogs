package smith.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.itsaky.androidide.logsender.LogSender;
import smith.lib.alerts.dialog.AlertSDialog;
import smith.lib.alerts.dialog.CustomSDialog;
import smith.lib.alerts.dialog.InputSDialog;
import smith.lib.alerts.dialog.LoadingSDialog;
import smith.lib.alerts.dialog.SDialog;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void input(View v) {
        InputSDialog sdialog = new InputSDialog(this);
        sdialog.setAccentColor(SDialog.DEFAULT_COLOR);
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
    
    public void alert(View vi) {
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
        sdialog.setAccentColor(SDialog.DEFAULT_COLOR);
        sdialog.setTheme(SDialog.LIGHT_THEME);
        sdialog.setCancelable(true);
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show();
    }
    
    public void loading(View vi) {
        LoadingSDialog sdialog = new LoadingSDialog(this);
        sdialog.setTitle("Loading SDialog!");
        sdialog.setText("Please wait a second...");
        sdialog.setAccentColor(SDialog.DEFAULT_COLOR);
        sdialog.setTheme(SDialog.LIGHT_THEME);
        sdialog.setOnDismissCallBack(() -> {
            Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show();
        });
        sdialog.show(6*1000);
    }
    
    public void custom() {
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
