package chooongg.libAndroid.core.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.basic.ext.getTClass

abstract class LibBindingModelActivity<B : ViewBinding, M : ViewModel> : LibBindingActivity<B>() {

    protected open fun getViewModelTIndex() = 1

    @Suppress("UNCHECKED_CAST", "LeakingThis")
    val model: M by ViewModelLazy(
        (javaClass.getTClass(getViewModelTIndex()) as Class<M>).kotlin,
        { viewModelStore },
        { defaultViewModelProviderFactory },
        { defaultViewModelCreationExtras }
    )

    /**
     * 初始化ViewModel的内容
     */
    protected open fun initContentForViewModel(savedInstanceState: Bundle?) {}

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initContentForViewModel(savedInstanceState)
    }
}