package smith.lib.alerts.dialog.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import smith.lib.alerts.dialog.ItemsSDialog
import smith.lib.alerts.dialog.R
import smith.lib.alerts.dialog.callbacks.OnItemClickCallBack

class SItemsAdapter(
    private var data: List<String>,
    var callback: OnItemClickCallBack?,
    var sdialog: ItemsSDialog
): RecyclerView.Adapter<SItemsAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.sitem_items, null)
        v.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, p: Int) {
        holder.text.text = data[p]
        holder.text.setTextColor(sdialog.accentColor)
        holder.main.setOnClickListener {
            if (callback != null) callback!!.onItemClick(data[p], p)
            sdialog.dismiss()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var text: TextView
        var main: LinearLayout

        init {
            text = v.findViewById(R.id.text)
            main = v.findViewById(R.id.main)
        }
    }
}
