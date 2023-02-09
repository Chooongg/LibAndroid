package chooongg.libAndroid.core.activity

import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.core.viewBinding.ActivityBinding
import chooongg.libAndroid.core.viewBinding.ActivityBindingDelegate

abstract class LibBindingActivity<B : ViewBinding> : LibActivity(),
    ActivityBinding<B> by ActivityBindingDelegate() {

    override fun initLayout() = ResourcesCompat.ID_NULL

    override fun onCreateContentView() {
        setContentViewWithBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeBinding()
    }
}