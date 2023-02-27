package chooongg.libAndroid.core.appBar

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import chooongg.libAndroid.core.R
import chooongg.libAndroid.core.widget.TopAppBar
import com.google.android.material.appbar.AppBarLayout

class SmallTopAppBarProvider : AppBarProvider {
    override fun createLayout(
        activity: AppCompatActivity?, fragment: Fragment?, contentView: View?
    ): View {
        val context = activity ?: fragment?.context
        ?: throw NullPointerException("Activity and Fragment cannot be empty at the same time")
        val coordinatorLayout = CoordinatorLayout(context)
        val appBarLayout = AppBarLayout(context).also {
            it.id = R.id.lib_appBarLayout
            it.fitsSystemWindows = true
            coordinatorLayout.addView(it, CoordinatorLayout.LayoutParams(-1, -2))
        }
        val topAppBar = TopAppBar(context).also {
            it.id = R.id.lib_topAppBar
            appBarLayout.addView(it, AppBarLayout.LayoutParams(-1, -2))
        }
        if (contentView != null) {
            coordinatorLayout.addView(
                contentView, CoordinatorLayout.LayoutParams(-1, -1).apply {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                })
        }
        activity?.setSupportActionBar(topAppBar)
        return coordinatorLayout
    }

    override fun getToolbarId() = R.id.lib_topAppBar
}