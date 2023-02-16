package chooongg.libAndroid.form

import androidx.collection.ArraySet
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import chooongg.libAndroid.basic.ext.hideIME

class FormAdapter {

    private val adapter =
        ConcatAdapter(ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build())

    internal var listener: FormEventListener? = null

    internal val itemTypeHold = ArraySet<Pair<Class<*>, Class<*>>>()

    fun bind(recyclerView: RecyclerView, listener: FormEventListener? = null) {
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (recyclerView.focusedChild != null && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    recyclerView.focusedChild.clearFocus()
                    recyclerView.context.hideIME()
                }
            }
        })
        this.listener = listener
    }
}