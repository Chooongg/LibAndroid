package chooongg.libAndroid.core.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import chooongg.libAndroid.basic.ext.attrDimensionPixelSize
import chooongg.libAndroid.basic.ext.getActivity
import chooongg.libAndroid.core.R
import chooongg.libAndroid.core.annotation.EdgeToEdge
import chooongg.libAndroid.core.widget.behavior.AppBarLayoutBehavior
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class TopAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    private val edgeToEdge = context::class.java.getAnnotation(EdgeToEdge::class.java)

    val appBarLayout =
        AppBarLayout(context, attrs, com.google.android.material.R.attr.appBarLayoutStyle).also {
            it.id = R.id.lib_appBarLayout
            it.fitsSystemWindows = edgeToEdge?.isEdgeToEdge == true
            addView(it, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                behavior = AppBarLayoutBehavior()
            })
        }

    var collapsingToolbarLayout: CollapsingToolbarLayout? = null
        private set

    val topAppBar =
        TopAppBar(context, attrs, com.google.android.material.R.attr.toolbarStyle).apply {
            id = R.id.lib_topAppBar
        }

    init {
        tag = "Lib_TopAppBarLayout"
        val a = context.obtainStyledAttributes(attrs, R.styleable.TopAppBarLayout, defStyleAttr, 0)
        createViewHierarchy(
            attrs,
            a.getInt(R.styleable.TopAppBarLayout_heightMode, 0),
            a.getBoolean(R.styleable.TopAppBarLayout_setActionBar, true)
        )
        if (a.getBoolean(R.styleable.TopAppBarLayout_syncStatusBarColor, true)
            && edgeToEdge?.isEdgeToEdge != true
        ) {
            context.getActivity()?.let {
                appBarLayout.addLiftOnScrollListener { _, backgroundColor ->
                    it.window.statusBarColor = backgroundColor
                }
            }
        }
        a.recycle()
    }

    private fun createViewHierarchy(attrs: AttributeSet?, heightMode: Int, setActionBar: Boolean) {
        if (heightMode == 0) {
            appBarLayout.addView(topAppBar, AppBarLayout.LayoutParams(-1, -2).apply {
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
            })
        } else {
            val defAttr = if (heightMode == 1) {
                com.google.android.material.R.attr.collapsingToolbarLayoutMediumStyle
            } else com.google.android.material.R.attr.collapsingToolbarLayoutLargeStyle
            collapsingToolbarLayout = CollapsingToolbarLayout(context, attrs, defAttr).also {
                it.id = R.id.lib_collapsingToolbarLayout
                val layoutHeight = attrDimensionPixelSize(
                    if (heightMode == 1) com.google.android.material.R.attr.collapsingToolbarLayoutMediumSize
                    else com.google.android.material.R.attr.collapsingToolbarLayoutLargeSize,
                    -2
                )
                appBarLayout.addView(
                    it, AppBarLayout.LayoutParams(-1, layoutHeight).apply {
                        scrollFlags =
                            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                    }
                )
                it.addView(
                    topAppBar,
                    CollapsingToolbarLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        attrDimensionPixelSize(androidx.appcompat.R.attr.actionBarSize, -1)
                    ).apply {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                    }
                )
            }
        }
        if (setActionBar) {
            (context.getActivity() as? AppCompatActivity)?.setSupportActionBar(topAppBar)
        }
    }

    fun addLiftOnScrollListener(listener: AppBarLayout.LiftOnScrollListener) {
        appBarLayout.addLiftOnScrollListener(listener)
    }

    fun removeLiftOnScrollListener(listener: AppBarLayout.LiftOnScrollListener) {
        appBarLayout.removeLiftOnScrollListener(listener)
    }
}