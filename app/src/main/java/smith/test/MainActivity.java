package smith.test;

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

        SDialog dialog = new SDialog.AlertSDialog(this)
            .setTitle("Title Of SDialog")
            .setText("Some dummy text.\nSDialog text here...")
            .setPositiveButton("OK", v -> {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancel", v -> {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            })
            .setNeutralButton("Hide", v -> {
                Toast.makeText(MainActivity.this, "hide", Toast.LENGTH_SHORT).show();
            })
            .setSDialogTheme(SDialog.LIGHT_THEME)
            .setButtonsColor("#A7B4C5")
            .create();
        dialog.setCancelable(true);
        dialog.show(2*60*1000);
    }
}
