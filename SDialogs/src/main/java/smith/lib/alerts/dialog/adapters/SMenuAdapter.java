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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import smith.lib.alerts.dialog.MenuSDialog;
import smith.lib.alerts.dialog.R;
import smith.lib.alerts.dialog.callbacks.OnItemClickCallback;
import smith.lib.alerts.dialog.utils.MenuSDialogItem;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class SMenuAdapter extends RecyclerView.Adapter<SMenuAdapter.ViewHolder> {

    private final List<MenuSDialogItem> data;
    private final OnItemClickCallback callback;
    private final MenuSDialog sdialog;

    public SMenuAdapter(List<MenuSDialogItem> data, OnItemClickCallback callback, MenuSDialog sdialog) {
        this.data = data;
        this.callback = callback;
        this.sdialog = sdialog;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitem_menu, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuSDialogItem item = data.get(position);

        holder.icon.setImageDrawable(item.getIcon());
        holder.icon.setColorFilter(sdialog.getAccentColor());

        holder.text.setText(item.getTitle());
        holder.text.setTextColor(sdialog.getAccentColor());

        holder.main.setOnClickListener(v -> {
            if (callback != null) callback.onItemClick(position, item);
            sdialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout main;
        ImageView icon;

        public ViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.sdialog_text);
            main = view.findViewById(R.id.sdialog_main);
            icon = view.findViewById(R.id.sdialog_icon);
        }
    }
}
