package chooongg.libAndroid.recyclerView.item

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class Item<VH : RecyclerView.ViewHolder> : LifecycleOwner {

    /**
     * 生命周期
     */
    private val lifecycleRegistry = LifecycleRegistry(this)

    /**
     * 是否启用
     */
    var isEnabled: Boolean = true

    /**
     * 是否选中
     */
    var isSelected: Boolean = false

    /**
     * 是否可选
     */
    var isSelectable: Boolean = true

    override val lifecycle: Lifecycle get() = lifecycleRegistry

    /**
     * 获取ViewHolder
     */
    abstract fun getViewHolder(context: Context, parent: ViewGroup?): VH

    /**
     * 绑定数据
     */
    @CallSuper
    fun bindView(holder: VH, payloads: List<Any>) {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    /**
     * 释放资源
     */
    fun unbindView(holder: VH) {}

    /**
     * 从窗口绑定
     */
    @CallSuper
    fun onItemViewAttachedToWindow(holder: VH) {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }


    /**
     * 从窗口解绑
     */
    fun onItemViewDetachedToWindow(holder: VH) {}

    /**
     * 视图处于暂时状态，无法回收
     */
    @CallSuper
    fun onItemViewRecycled(holder: VH) {
        if (lifecycleRegistry.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            lifecycleRegistry.currentState = Lifecycle.State.CREATED
        }
    }
}