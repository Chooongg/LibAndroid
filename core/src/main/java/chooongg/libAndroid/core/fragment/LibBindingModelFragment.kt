package chooongg.libAndroid.core.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.basic.ext.getTClass

abstract class LibBindingModelFragment<B : ViewBinding, M : ViewModel> : LibBindingFragment<B>() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentForViewModel(savedInstanceState)
    }
}