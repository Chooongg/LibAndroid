package chooongg.libAndroid.core.appBar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import chooongg.libAndroid.core.R
import com.google.android.material.appbar.AppBarLayout
import java.security.AccessController.getContext

class TopMediumAppBarDelegate : AppBarDelegate {
    override fun createLayout(
        activity: AppCompatActivity?, fragment: Fragment?, container: ViewGroup?, contentView: View?
    ): View {
        val view = inflate(
            activity, fragment, R.layout.lib_appbar_top_medium, container
        ) as CoordinatorLayout
        view.addView(contentView, CoordinatorLayout.LayoutParams(-1, -1).apply {
            behavior = AppBarLayout.ScrollingViewBehavior()
        })
        activity?.setSupportActionBar(view.findViewById(R.id.lib_topAppBar))
        return view
    }

    override fun getToolbarId() = R.id.lib_topAppBar
}