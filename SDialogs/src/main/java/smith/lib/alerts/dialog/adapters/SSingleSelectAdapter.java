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

package smith.lib.alerts.dialog.adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.SingleSelectSDialog;
import smith.lib.alerts.dialog.callbacks.OnSingleSelectCallback;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class SSingleSelectAdapter extends RecyclerView.Adapter<SSingleSelectAdapter.ViewHolder> {

    private final List<Map<String, Object>> data;
    private final OnSingleSelectCallback callback;
    private final SingleSelectSDialog sdialog;

    public SSingleSelectAdapter(List<Map<String, Object>> data, OnSingleSelectCallback callback, SingleSelectSDialog sdialog) {
        this.data = data;
        this.callback = callback;
        this.sdialog = sdialog;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_radios, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int p) {
        holder.choice.setText(Objects.requireNonNull(data.get(p).get("text")).toString());
        holder.choice.setTextColor(sdialog.getTextColor());
        holder.choice.setButtonTintList(ColorStateList.valueOf(sdialog.getAccentColor()));
        holder.choice.setChecked((Boolean) Objects.requireNonNull(data.get(p).get("checked")));
        holder.choice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (callback != null)
                callback.onSelect((int) Objects.requireNonNull(data.get(p).get("id")),
                        Objects.requireNonNull(data.get(p).get("text")).toString());
            sdialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton choice;
        LinearLayout main;

        public ViewHolder(View v) {
            super(v);
            choice = v.findViewById(R.id.sdialog_choice);
            main = v.findViewById(R.id.sdialog_main);
        }
    }
}