package chooongg.libAndroid.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import chooongg.libAndroid.basic.ext.logDClass
import chooongg.libAndroid.basic.ext.resString
import chooongg.libAndroid.core.activity.LibActivity
import chooongg.libAndroid.core.annotation.Title

abstract class LibFragment : Fragment() {

    val fragment get() = this
    val libActivity get() = activity as? LibActivity

    var isLoaded = false; private set
    val isShowing get() = !isHidden && isResumed

    open var title: CharSequence? = null

    @LayoutRes
    protected abstract fun initLayout(): Int

    protected open fun initView(savedInstanceState: Bundle?) {}

    protected open fun initContent(savedInstanceState: Bundle?) {}

    protected open fun initContentByLazy() {}

    open fun onRefresh(any: Any? = null) {}

    open fun onReselected() = Unit

    open fun getLiftOnScrollTargetId() = ResourcesCompat.ID_NULL

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onCreateTitle()
        return onCreateContentView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
        initView(savedInstanceState)
        initContent(savedInstanceState)
        logDClass("Fragment", javaClass, "---------- $title ---------- onCreateView")
    }

    protected open fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(initLayout(), container, false)
    }

    private fun onCreateTitle() {
        if (title != null) return
        if (javaClass.isAnnotationPresent(Title::class.java)) {
            title = javaClass.getAnnotation(Title::class.java)!!.value
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!isLoaded && !isHidden) {
            isLoaded = true
            initContentByLazy()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            isLoaded = true
            initContentByLazy()
        }
        onBackPressedCallback.isEnabled = onBackPressedInterceptEnable
    }

    override fun onPause() {
        super.onPause()
        onBackPressedCallback.isEnabled = false
    }

    var onBackPressedInterceptEnable = false
        set(value) {
            field = value
            if (isResumed) onBackPressedCallback.isEnabled = value
        }

    private val onBackPressedCallback =
        object : OnBackPressedCallback(onBackPressedInterceptEnable) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }

    open fun setTitle(@StringRes resId: Int) = apply {
        lifecycleScope.launchWhenCreated {
            title = resString(resId)
        }
    }

    protected open fun onBackPressed() {}

    override fun onDestroyView() {
        super.onDestroyView()
        logDClass("Fragment", javaClass, "---------- $title ---------- onDestroyView")
    }
}