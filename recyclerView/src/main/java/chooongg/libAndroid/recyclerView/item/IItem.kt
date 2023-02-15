package chooongg.libAndroid.recyclerView.item

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface IItem<VH : RecyclerView.ViewHolder> {

    /**
     * 是否启用
     */
    var itemIsEnabled: Boolean

    /**
     * 是否选中
     */
    var itemIsSelected: Boolean

    /**
     * 是否可选
     */
    var itemIsSelectable: Boolean

    /**
     * 获取ViewHolder
     */
    fun getViewHolder(context: Context, parent: ViewGroup?): VH

    /**
     * 绑定数据
     */
    fun bindView(holder: VH, payloads: List<Any>)

    /**
     * 释放资源
     */
    fun unbindView(holder: VH)

    /**
     * 从窗口绑定
     */
    fun attachToWindow(holder: VH)

    /**
     * 从窗口解绑
     */
    fun detachFromWindow(holder: VH)

    /**
     * 视图处于暂时状态，无法回收
     */
    fun failedToRecycle(holder: VH): Boolean
}