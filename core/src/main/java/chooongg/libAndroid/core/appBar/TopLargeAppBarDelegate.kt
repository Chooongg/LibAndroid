package chooongg.libAndroid.core.appBar

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import chooongg.libAndroid.core.R
import chooongg.libAndroid.core.widget.TopAppBar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomappbar.BottomAppBar

class TopLargeAppBarDelegate : AppBarDelegate {
    override fun createLayout(
        activity: AppCompatActivity?, fragment: Fragment?, container: ViewGroup?, contentView: View?
    ): View {
        val view = inflate(
            activity, fragment, R.layout.lib_appbar_top_large, container
        ) as CoordinatorLayout
        contentView?.also {
            view.addView(it, CoordinatorLayout.LayoutParams(-1, -1).apply {
                behavior = AppBarLayout.ScrollingViewBehavior()
            })
            val paddingLeft = it.paddingLeft
            val paddingRight = it.paddingRight
            val collapsingToolbar =
                view.findViewById<CollapsingToolbarLayout>(R.id.lib_collapsingToolbarLayout)
            val expandedTitleMarginStart = collapsingToolbar.expandedTitleMarginStart
            val expandedTitleMarginEnd = collapsingToolbar.expandedTitleMarginEnd
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val inset = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                val isRtl = ViewCompat.getLayoutDirection(v) == ViewCompat.LAYOUT_DIRECTION_RTL
                collapsingToolbar.expandedTitleMarginStart =
                    expandedTitleMarginStart + if (isRtl) inset.right else inset.left
                collapsingToolbar.expandedTitleMarginEnd =
                    expandedTitleMarginEnd + if (isRtl) inset.left else inset.right
                view.findViewById<TopAppBar>(R.id.lib_topAppBar)
                    .updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        marginStart = if (isRtl) inset.right else inset.left
                        marginEnd = if (isRtl) inset.left else inset.right
                    }
                v.updatePadding(
                    left = paddingLeft + inset.left,
                    right = paddingRight + inset.right
                )
                insets
            }
        }
        activity?.setSupportActionBar(view.findViewById(R.id.lib_topAppBar))
        return view
    }

    override fun getToolbarId() = R.id.lib_topAppBar
}