package smith.lib.alerts.dialog.adapters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.Contract
import smith.lib.alerts.dialog.MultiSelectSDialog
import smith.lib.alerts.dialog.R

class SMultiSelectAdapter(private var data: List<Map<String, Any>>, var sdialog: MultiSelectSDialog):
    RecyclerView.Adapter<SMultiSelectAdapter.ViewHolder>() {

    private var checkedList: MutableList<Map<String, Any>> = ArrayList()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sitem_checkboxes, null)
        v.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
        holder.choice.text = data[p]["text"].toString()
        holder.choice.setTextColor(sdialog.textColor)
        holder.choice.buttonTintList = ColorStateList.valueOf(sdialog.accentColor)
        val checked = data[p]["checked"] as Boolean
        holder.choice.isChecked = checked

        if (!contains(checkedList, data[p]) && checked) checkedList.add(data[p])

        if (contains(checkedList, data[p]) && !checked) checkedList.remove(data[p])

        holder.choice.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (!contains(checkedList, data[p]) && isChecked) checkedList.add(data[p])
            if (contains(checkedList, data[p]) && !isChecked) checkedList.remove(data[p])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @Contract(pure = true)
    private fun contains(list: List<Map<String, Any>>, item: Map<String, Any>): Boolean {
        return list.contains(item)
    }

    val checkedItems: List<Map<String, Any>> get() = checkedList

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var choice: CheckBox
        var main: LinearLayout

        init {
            choice = v.findViewById(R.id.choice)
            main = v.findViewById(R.id.main)
        }
    }
}