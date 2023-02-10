package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.core.fragment.LibBindingFragment
import chooongg.libAndroid.core.widget.TopAppBarLayout
import com.chooongg.libAndroid.R
import com.google.android.material.appbar.AppBarLayout

abstract class BaseMainFragment<B : ViewBinding> : LibBindingFragment<B>(),
    AppBarLayout.LiftOnScrollListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<TopAppBarLayout>(R.id.topAppBarLayout)?.appBarLayout?.let {
//            when (val background = it.background) {
//                is ColorDrawable -> activity?.window?.statusBarColor = background.color
//                is MaterialShapeDrawable -> activity?.window?.statusBarColor =
//                    background.fillColor?.defaultColor ?: 0
//            }
//            it.addLiftOnScrollListener(this)
//        }
    }

    override fun onResume() {
        super.onResume()
        val appBarLayout = view?.findViewById<TopAppBarLayout>(R.id.topAppBarLayout)?.appBarLayout
        appBarLayout?.addLiftOnScrollListener(this)
    }

    override fun onStop() {
        super.onStop()
        val appBarLayout = view?.findViewById<TopAppBarLayout>(R.id.topAppBarLayout)?.appBarLayout
        appBarLayout?.removeLiftOnScrollListener(this)
    }

    override fun onUpdate(elevation: Float, backgroundColor: Int) {
        activity?.window?.statusBarColor = backgroundColor
    }
}