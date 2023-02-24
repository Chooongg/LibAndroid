package chooongg.libAndroid.form

import android.view.ViewGroup
import androidx.recyclerview.widget.*
import chooongg.libAndroid.basic.ext.hideIME
import chooongg.libAndroid.form.cteator.FormPartCreator
import chooongg.libAndroid.form.item.FormGroupTitle
import chooongg.libAndroid.form.item.FormItem
import chooongg.libAndroid.form.style.FormStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.lang.ref.WeakReference

class FormPartAdapter(
    private val globalAdapter: FormAdapter,
    private val style: FormStyle
) : RecyclerView.Adapter<FormViewHolder>() {

    companion object {
        const val NOTIFY_PAYLOADS = "update"
    }

    private var _recyclerView: WeakReference<RecyclerView>? = null

    val recyclerView get() = _recyclerView?.get()

    var adapterScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    internal lateinit var creator: FormPartCreator

    private val asyncDiffer = AsyncListDiffer(object : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) = Unit
        override fun onRemoved(position: Int, count: Int) = notifyItemRangeRemoved(position, count)
        override fun onInserted(position: Int, count: Int) =
            notifyItemRangeInserted(position, count)

        override fun onMoved(fromPosition: Int, toPosition: Int) =
            notifyItemMoved(fromPosition, toPosition)

    }, AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<FormItem>() {
        override fun areContentsTheSame(oldItem: FormItem, newItem: FormItem) = oldItem == newItem
        override fun areItemsTheSame(oldItem: FormItem, newItem: FormItem) =
            if (oldItem !is FormGroupTitle || newItem !is FormGroupTitle) {
                oldItem.name == newItem.name && oldItem.field == newItem.field
            } else true
    }).build())


    val data: List<FormItem> = asyncDiffer.currentList

    fun submitList(creator: FormPartCreator) {
        adapterScope.cancel()
        adapterScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        this.creator = creator
        update()
    }

    fun update(isHasPayloads: Boolean = false, block: (() -> Unit)? = null) {
        val partIndex = globalAdapter.indexPartOf(this)
        val partIsFirst = partIndex <= 0
        val partIsLast = partIndex >= globalAdapter.partCount() - 1
        val list = mutableListOf<FormItem>()
        creator.partList.forEachIndexed { groupIndex, group ->
            var index = 0
            val partName = if (creator.dynamicPart) {
                creator.dynamicPartNameFormatBlock?.invoke(creator.partName, groupIndex)
                    ?: "${creator.partName ?: ""}${partIndex + 1}"
            } else creator.partName
            // 添加片段标题
            if (creator.partName != null || creator.dynamicPart) {
                list.add(FormGroupTitle(partName ?: "").apply {

                })
                index++
            }
            group@ for (item in group) {
                if (!item.isRealVisible(globalAdapter)) continue@group
                if (!item.isOnEdgeToDisplay) {
                    if ((creator.partName != null || creator.dynamicPart) && index <= 1) continue@group
                    if (index == 0) continue@group
                }
                list.add(item)
                index++
            }
        }
        asyncDiffer.submitList(list) {
            notifyItemRangeChanged(0, list.size, if (isHasPayloads) NOTIFY_PAYLOADS else null)
            block?.invoke()
        }
    }

    override fun getItemViewType(position: Int) = globalAdapter.getItemViewType(
        style,
        if (data[position].isNeedTypeset) data[position].typeset ?: style.defaultTypeset else null,
        data[position]
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val itemType = globalAdapter.findItemViewTypeByInt(viewType)
            ?: throw NullPointerException("未找到对应的 FormItemViewType")
        val parentLayout = itemType.style.onCreateItemParentLayout(parent)
        val view = if (parentLayout == null) {
            if (itemType.typeset == null) itemType.item.onCreateItemView(parent)
            else {
                val typesetLayout = itemType.typeset.onCreateItemTypesetLayout(parent)
                typesetLayout.addView(itemType.item.onCreateItemView(typesetLayout))
                typesetLayout
            }
        } else {
            if (itemType.typeset == null) itemType.item.onCreateItemView(parentLayout)
            else {
                val typesetLayout = itemType.typeset.onCreateItemTypesetLayout(parent)
                typesetLayout.addView(itemType.item.onCreateItemView(typesetLayout))
                parentLayout.addView(typesetLayout)
                parentLayout
            }
        }
        return FormViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        val item = data[position]
        val parentLayout = style.onBindItemParentLayout(holder.itemView as ViewGroup, item)
        if (item.isNeedTypeset) {
            (item.typeset ?: style.defaultTypeset).onBindItemTypesetLayout(parentLayout, item)
        }
        item.onBindItemView(holder, null)
    }

    override fun onBindViewHolder(
        holder: FormViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = data[position]
        val parentLayout = style.onBindItemParentLayout(holder.itemView as ViewGroup, item)
        (item.typeset ?: style.defaultTypeset).onBindItemTypesetLayout(parentLayout, item)
        item.onBindItemView(holder, payloads)
    }

    override fun onViewRecycled(holder: FormViewHolder) {
        holder.clear()
    }

    override fun getItemCount() = asyncDiffer.currentList.size


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        _recyclerView = WeakReference(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this._recyclerView = null
        adapterScope.cancel()
        adapterScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }

    fun clearFocus() {
        recyclerView?.focusedChild?.clearFocus()
        recyclerView?.context?.hideIME()
    }
}