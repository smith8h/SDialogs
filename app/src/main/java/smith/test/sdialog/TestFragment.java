package smith.test.sdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import smith.lib.alerts.dialog.AlertSDialog;
import smith.lib.alerts.dialog.SDialog;

public class TestFragment extends Fragment {

    public TestFragment() {}

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        TextView textView = view.findViewById(R.id.text);
        textView.setOnClickListener(view1 -> {
            AlertSDialog sDialog = new AlertSDialog(getActivity());
            sDialog.setIconDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ok_img));
            sDialog.setTitle("Title Of SDialog");
            sDialog.setText(getString(R.string.dummy_text));
            sDialog.setPositiveButton("OK", () -> {
                Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                sDialog.dismiss();
            });
            sDialog.setNegativeButton("Cancel", () -> {
                Toast.makeText(getActivity(), "cancel", Toast.LENGTH_SHORT).show();
                sDialog.dismiss();
            });
            sDialog.setNeutralButton("Hide", () -> {
                Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
                sDialog.dismiss();
            });
            sDialog.setAccentColor(0xFFAD97BE);
            sDialog.setTheme(SDialog.THEME_BY_SYSTEM);
            sDialog.setCancelable(true);
            sDialog.setOnDismissCallBack(() -> {
                Toast.makeText(getActivity(), "onDismiss", Toast.LENGTH_SHORT).show();
            });
            sDialog.show();
        });

        return view;
    }
}
