package chooongg.libAndroid.recyclerView.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.basic.ext.getTClass
import chooongg.libAndroid.recyclerView.viewHolder.BindingViewHolder

abstract class BindingItem<B : ViewBinding> : IItem<BindingViewHolder<B>> {

    protected open fun getViewBindingTIndex() = 0

    override var itemIsEnabled: Boolean = true
    override var itemIsSelected: Boolean = false
    override var itemIsSelectable: Boolean = true

    @Suppress("UNCHECKED_CAST")
    override fun getViewHolder(context: Context, parent: ViewGroup?): BindingViewHolder<B> {
        val clazz = javaClass.getTClass(getViewBindingTIndex()) as Class<B>
        val method = clazz.getMethod(
            "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
        )
        val b = method.invoke(null, LayoutInflater.from(context), parent, false) as B
        return BindingViewHolder(b)
    }

    override fun bindView(holder: BindingViewHolder<B>, payloads: List<Any>) {}

    override fun unbindView(holder: BindingViewHolder<B>) {}

    override fun attachToWindow(holder: BindingViewHolder<B>) {}

    override fun detachFromWindow(holder: BindingViewHolder<B>) {}

    override fun failedToRecycle(holder: BindingViewHolder<B>): Boolean {
        return false
    }
}