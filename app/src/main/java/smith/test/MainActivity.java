package smith.test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.itsaky.androidide.logsender.LogSender;
import smith.lib.alerts.dialog.SDialog;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    
    public void alert(View vi) {
        SDialog sdialog = new SDialog.AlertSDialog(this)
            .setTitle("Title Of SDialog")
            .setText("Some dummy text.\nSDialog text here...")
            .setPositiveButton("OK", () -> {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancel", () -> {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            })
            .setNeutralButton("Hide", () -> {
                Toast.makeText(MainActivity.this, "hide", Toast.LENGTH_SHORT).show();
            })
            .setButtonsColor(0xFFA7B4C5)
            .setSDialogTheme(SDialog.LIGHT_THEME)
            .create();
        sdialog.setCancelable(true);
        sdialog.show();
    }
    
    public void loading(View vi) {
        SDialog sdialog = new SDialog.LoadingSDialog(this)
            .setTitle("Loading SDialog...")
            .setText("Please wait.")
            .setLoadingColor(0xFFA7B4C5)
            .setSDialogTheme(SDialog.LIGHT_THEME)
            .create();
        sdialog.setCancelable(false);
        sdialog.setOnDismissCallBack(() -> Toast.makeText(this, "onDismiss", Toast.LENGTH_SHORT).show());
        sdialog.show(10*1000);
    }
}
