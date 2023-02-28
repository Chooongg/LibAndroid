package chooongg.libAndroid.core.appBar

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * AppBar布局代理
 */
interface AppBarDelegate {

    fun inflate(
        activity: Activity?, fragment: Fragment?, @LayoutRes idRes: Int, container: ViewGroup?
    ): View {
        return activity?.layoutInflater?.inflate(idRes, container, false)
            ?: fragment?.layoutInflater?.inflate(idRes, container, false)
            ?: throw NullPointerException("Activity and Fragment cannot be empty at the same time")
    }

    /**
     * 创建布局并填充到Activity
     */
    fun createLayout(
        activity: AppCompatActivity?,
        fragment: Fragment?,
        container: ViewGroup?,
        contentView: View?
    ): View

    /**
     * 获取Toolbar的ID
     */
    @IdRes
    fun getToolbarId(): Int? = null
}