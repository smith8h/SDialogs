package smith.lib.alerts.dialog.adapters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import smith.lib.alerts.dialog.R
import smith.lib.alerts.dialog.SingleSelectSDialog
import smith.lib.alerts.dialog.callbacks.OnSingleSelectCallBack

class SSingleSelectAdapter(private var data: List<Map<String, Any>>, var callback: OnSingleSelectCallBack?, var sdialog: SingleSelectSDialog):
    RecyclerView.Adapter<SSingleSelectAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sitem_radios, null)
        v.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
        holder.choice.text = data[p]["text"].toString()
        holder.choice.setTextColor(sdialog.textColor)
        holder.choice.buttonTintList = ColorStateList.valueOf(sdialog.accentColor)
        holder.choice.isChecked = data[p]["checked"] as Boolean
        holder.choice.setOnCheckedChangeListener { _: CompoundButton?, _: Boolean ->
            if (callback != null) callback!!.onSelect(data[p]["id"] as Int, data[p]["text"].toString())
            sdialog.dismiss()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var choice: RadioButton
        var main: LinearLayout

        init {
            choice = v.findViewById(R.id.choice)
            main = v.findViewById(R.id.main)
        }
    }
}