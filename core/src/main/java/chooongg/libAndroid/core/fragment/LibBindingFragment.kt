package chooongg.libAndroid.core.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.core.ext.getBindingT

abstract class LibBindingFragment<B : ViewBinding> : LibFragment() {

    protected open fun getViewBindingTIndex() = 0

    protected val binding: B get() = _binding!!

    private var _binding: B? = null

    override fun initLayout() = ResourcesCompat.ID_NULL

    override fun getContentView(container: ViewGroup?): View? {
        return getBindingT<B>(getViewBindingTIndex(), layoutInflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}