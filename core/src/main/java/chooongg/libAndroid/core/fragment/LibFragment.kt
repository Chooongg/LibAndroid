package chooongg.libAndroid.core.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import chooongg.libAndroid.basic.ext.BasicLog
import chooongg.libAndroid.basic.ext.attrBoolean
import chooongg.libAndroid.basic.ext.getLogcatPath
import chooongg.libAndroid.basic.ext.resString
import chooongg.libAndroid.core.R
import chooongg.libAndroid.core.activity.LibActivity
import chooongg.libAndroid.core.annotation.AppBarEnable
import chooongg.libAndroid.core.annotation.AppBarNavigationEnable
import chooongg.libAndroid.core.appBar.AppBarDelegate
import chooongg.libAndroid.core.appBar.TopSmallAppBarDelegate

abstract class LibFragment : Fragment(), LifecycleEventObserver {

    val fragment get() = this
    val libActivity get() = activity as? LibActivity

    var isLoaded = false; private set
    val isShowing get() = !isHidden && isResumed

    open var title: CharSequence? = null
        set(value) {
            field = value
            val toolbarId = getToolbarId() ?: return
            val view = view ?: return
            view.findViewById<Toolbar>(toolbarId).title = value
        }

    /**
     * 获取AppBar布局提供器
     */
    protected open fun getAppBarDelegate(): AppBarDelegate = TopSmallAppBarDelegate()

    /**
     * 初始化布局
     */
    @LayoutRes
    protected abstract fun initLayout(): Int

    /**
     * 获取Toolbar的id
     */
    @IdRes
    protected open fun getToolbarId(): Int? = getAppBarDelegate().getToolbarId()

    /**
     * 初始化视图
     */
    protected open fun initView(savedInstanceState: Bundle?) {}

    /**
     * 初始化内容
     */
    protected open fun initContent(savedInstanceState: Bundle?) {}

    /**
     * 惰性初始化内容
     */
    protected open fun initContentByLazy() {}

    /**
     * 刷新时
     */
    open fun onRefresh(any: Any? = null) {}

    /**
     * 再次选中时
     */
    open fun onReselected() = Unit

    open fun getLiftOnScrollTargetId() = ResourcesCompat.ID_NULL

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (BasicLog.isEnable) lifecycle.addObserver(this)
        return createContentViewByLib(container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
        initView(savedInstanceState)
        initContent(savedInstanceState)
        getToolbarId()?.also { view.findViewById<Toolbar>(it)?.title = title }
    }

    internal open fun getContentView(container: ViewGroup?): View? {
        return layoutInflater.inflate(initLayout(), container, false)
    }

    private fun createContentViewByLib(container: ViewGroup?): View? {
        val view = if (!attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)
            && javaClass.getAnnotation(AppBarEnable::class.java)?.value == true
        ) getAppBarDelegate().createLayout(null, this, container, getContentView(container))
        else getContentView(container)
        val toolbarId = getToolbarId() ?: return view
        if (javaClass.isAnnotationPresent(AppBarNavigationEnable::class.java)) {
            if (javaClass.getAnnotation(AppBarNavigationEnable::class.java)!!.value) {
                view?.findViewById<Toolbar>(toolbarId)?.also {
                    it.setNavigationIcon(R.drawable.ic_arrow_back)
                    it.setNavigationOnClickListener {
                        if (onBackPressedCallback.isEnabled) {
                            onBackPressed()
                        } else activity?.onBackPressedDispatcher?.onBackPressed()
                    }
                }
            }
        } else if (activity?.javaClass?.getAnnotation(AppBarNavigationEnable::class.java)?.value != false) {
            view?.findViewById<Toolbar>(toolbarId)?.also {
                it.setNavigationIcon(R.drawable.ic_arrow_back)
                it.setNavigationOnClickListener {
                    if (onBackPressedCallback.isEnabled) {
                        onBackPressed()
                    } else activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }
        }

        return view
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
        title = resString(resId)
    }

    protected open fun onBackPressed() {}

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(this)
    }

    final override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} ${event.name}"
        )
    }
}