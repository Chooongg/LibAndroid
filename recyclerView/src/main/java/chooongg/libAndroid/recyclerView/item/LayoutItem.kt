package chooongg.libAndroid.recyclerView.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import chooongg.libAndroid.recyclerView.viewHolder.LayoutViewHolder

abstract class LayoutItem : Item<LayoutViewHolder>() {

    @get:LayoutRes
    abstract val layoutRes: Int

    override fun getViewHolder(context: Context, parent: ViewGroup?): LayoutViewHolder {
        return LayoutViewHolder(LayoutInflater.from(context).inflate(layoutRes, parent, false))
    }
}