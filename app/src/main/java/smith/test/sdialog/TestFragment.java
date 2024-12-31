/*
 * Created by Dev. Smith (Hussein Shakir) on 9/17/23, 8:01 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/16/23, 10:13 PM
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

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import smith.lib.alerts.dialog.AlertSDialog;
import smith.lib.alerts.dialog.utils.SDialogUtils;

public class TestFragment extends Fragment {

    public TestFragment() {}

    @Nullable
    @Override
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
            sDialog.setTheme(SDialogUtils.THEME_BY_SYSTEM);
            sDialog.setCancelable(true);
            sDialog.setOnDismissCallback(() -> {
                Toast.makeText(getActivity(), "onDismiss", Toast.LENGTH_SHORT).show();
            });
            sDialog.show();
        });

        return view;
    }
}
