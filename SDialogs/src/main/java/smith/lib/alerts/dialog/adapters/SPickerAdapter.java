/*
 * Created by Dev. Smith (Hussein Shakir) on 9/28/23, 8:29 PM
 * Copyright Ⓒ 2023. All rights reserved Ⓒ 2023 http://github.com/smith8h
 * Last modified: 9/28/23, 8:29 PM
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

package smith.lib.alerts.dialog.adapters;

import android.annotation.SuppressLint;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.recyclerview.widget.RecyclerView;
import smith.lib.alerts.dialog.R;

@SuppressWarnings({"unused"})
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class SPickerAdapter extends RecyclerView.Adapter<SPickerAdapter.ViewHolder> {

    private final String[] data;
    private final int textColor;

    public SPickerAdapter(String[] data, int textColor) {
        this.data = data;
        this.textColor = textColor;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public SPickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_picker, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new SPickerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPickerAdapter.ViewHolder holder, int position) {
        holder.text.setText(data[position]);
        holder.text.setTextColor(textColor);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout main;

        public ViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.sdialog_text);
            main = view.findViewById(R.id.sdialog_main);
        }
    }
}
