package chooongg.libAndroid.core.appBar

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import chooongg.libAndroid.core.R
import chooongg.libAndroid.core.widget.TopAppBar
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.max

class TopSmallAppBarDelegate : AppBarDelegate {
    override fun createLayout(
        activity: AppCompatActivity?, fragment: Fragment?, container: ViewGroup?, contentView: View?
    ): View {
        val view = inflate(
            activity, fragment, R.layout.lib_appbar_top_small, container
        ) as CoordinatorLayout
        contentView?.also {
            view.addView(it, CoordinatorLayout.LayoutParams(-1, -1).apply {
                behavior = AppBarLayout.ScrollingViewBehavior()
            })
//            val paddingLeft = it.paddingLeft
//            val paddingRight = it.paddingRight
//            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
//                val displayCutout =
//                    insets.displayCutout ?: return@setOnApplyWindowInsetsListener insets
//                val isRtl = ViewCompat.getLayoutDirection(v) == ViewCompat.LAYOUT_DIRECTION_RTL
//                view.findViewById<TopAppBar>(R.id.lib_topAppBar)
//                    .updateLayoutParams<MarginLayoutParams> {
//                        marginStart =
//                            if (isRtl) displayCutout.safeInsetRight else displayCutout.safeInsetLeft
//                        marginEnd =
//                            if (isRtl) displayCutout.safeInsetLeft else displayCutout.safeInsetRight
//                    }
//                v.updatePadding(
//                    left = paddingLeft + max(0, displayCutout.safeInsetLeft - v.x.toInt()),
//                    right = paddingRight + displayCutout.safeInsetRight
//                )
//                insets
//            }
        }
        activity?.setSupportActionBar(view.findViewById(R.id.lib_topAppBar))
        return view
    }

    override fun getToolbarId() = R.id.lib_topAppBar
}