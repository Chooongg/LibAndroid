package chooongg.libAndroid.core.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import chooongg.libAndroid.basic.ext.getGenericsClass

abstract class LibModelActivity<M : ViewModel> : LibActivity() {

    protected open fun getViewModelTIndex() = 1

    @Suppress("UNCHECKED_CAST", "LeakingThis")
    val model: M by ViewModelLazy(
        (javaClass.getGenericsClass(getViewModelTIndex()) as Class<M>).kotlin,
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