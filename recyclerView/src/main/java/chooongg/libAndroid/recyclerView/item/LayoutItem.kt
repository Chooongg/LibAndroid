package chooongg.libAndroid.recyclerView.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import chooongg.libAndroid.recyclerView.viewHolder.LayoutViewHolder

abstract class LayoutItem : IItem<LayoutViewHolder> {

    @get:LayoutRes
    abstract val layoutRes: Int

    override var itemIsEnabled: Boolean = true
    override var itemIsSelected: Boolean = false
    override var itemIsSelectable: Boolean = true

    override fun getViewHolder(context: Context, parent: ViewGroup?): LayoutViewHolder {
        return LayoutViewHolder(LayoutInflater.from(context).inflate(layoutRes, parent, false))
    }

    override fun bindView(holder: LayoutViewHolder, payloads: List<Any>) {}

    override fun unbindView(holder: LayoutViewHolder) {}

    override fun attachToWindow(holder: LayoutViewHolder) {}

    override fun detachFromWindow(holder: LayoutViewHolder) {}

    override fun failedToRecycle(holder: LayoutViewHolder): Boolean {
        return false
    }
}