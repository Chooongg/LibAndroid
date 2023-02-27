package chooongg.libAndroid.core.activity

import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.core.ext.getBindingT

abstract class LibBindingActivity<B : ViewBinding> : LibActivity() {

    protected open fun getViewBindingTIndex() = 0

    protected val binding: B get() = _binding!!

    private var _binding: B? = null

    override fun initLayout() = ResourcesCompat.ID_NULL

    override fun getContentView(): View {
        return getBindingT<B>(getViewBindingTIndex()).apply { _binding = this }.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}