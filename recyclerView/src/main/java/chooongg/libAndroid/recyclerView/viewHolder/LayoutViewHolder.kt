package chooongg.libAndroid.recyclerView.viewHolder

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open class LayoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val context get() = itemView.context

    private val views: SparseArray<View> = SparseArray()

    open fun <T : View> getView(@IdRes viewId: Int): T {
        val view = getViewOrNull<T>(viewId)
        checkNotNull(view) { "No view found with id $viewId" }
        return view
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
        val view = views.get(viewId)
        if (view == null) {
            itemView.findViewById<T>(viewId)?.let {
                views.put(viewId, it)
                return it
            }
        }
        return view as? T
    }

    open fun <T : View> Int.findView(): T? {
        return itemView.findViewById(this)
    }
}