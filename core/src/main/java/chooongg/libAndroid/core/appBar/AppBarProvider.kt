package chooongg.libAndroid.core.appBar

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * AppBar布局提供器
 */
interface AppBarProvider {
    /**
     * 创建布局并填充到Activity
     */
    fun createLayout(activity: AppCompatActivity?, fragment: Fragment?, contentView: View?): View

    /**
     * 获取Toolbar的ID
     */
    @IdRes
    fun getToolbarId(): Int? = null
}