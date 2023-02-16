package chooongg.libAndroid.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chooongg.libAndroid.recyclerView.item.Item

abstract class LibAdapter<T : Item<out RecyclerView.ViewHolder>> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return data!![viewType].getViewHolder(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}