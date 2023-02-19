package chooongg.libAndroid.form

import androidx.collection.ArraySet
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chooongg.libAndroid.basic.ext.hideIME
import chooongg.libAndroid.form.cteator.FormPartCreator
import chooongg.libAndroid.form.item.FormItem
import chooongg.libAndroid.form.style.FormStyle
import chooongg.libAndroid.form.typeset.FormTypeset

class FormAdapter(
    @androidx.annotation.IntRange(from = 1) val spanSize: Int,
    isEditable: Boolean
) {

    private val adapter =
        ConcatAdapter(ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build())

    var isEditable: Boolean = isEditable
        set(value) {
            field = value
            adapter.notifyItemRangeChanged(0, adapter.itemCount)
        }

    internal var listener: FormEventListener? = null

    private val itemTypeHold = ArraySet<FormItemViewType>()

    fun bind(recyclerView: RecyclerView, listener: FormEventListener? = null) {
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, spanSize)
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

    fun addPart(style: FormStyle, block: FormPartCreator.() -> Unit) {
        adapter.addAdapter(FormPartAdapter(this, style).apply {
            submitList(FormPartCreator().apply(block))
        })
    }

    /**
     * 获取项目视图的类型数值
     */
    internal fun getItemViewType(style: FormStyle, typeset: FormTypeset?, item: FormItem): Int {
        val itemType = FormItemViewType(style, typeset, item)
        if (!itemTypeHold.contains(itemType)) {
            itemTypeHold.add(itemType)
        }
        return itemTypeHold.indexOf(itemType)
    }

    /**
     * 根据数值获取项目视图的类型
     */
    internal fun findItemViewTypeByInt(itemType: Int) = itemTypeHold.valueAt(itemType)

    fun indexPartOf(part: FormPartAdapter): Int {
        return adapter.adapters.indexOf(part)
    }

    fun partCount(): Int = adapter.adapters.size
}