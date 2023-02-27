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
import chooongg.libAndroid.basic.ext.attrBoolean
import chooongg.libAndroid.basic.ext.getLogcatPath
import chooongg.libAndroid.basic.ext.resString
import chooongg.libAndroid.core.activity.LibActivity
import chooongg.libAndroid.core.annotation.AppBarEnable
import chooongg.libAndroid.core.appBar.AppBarProvider
import chooongg.libAndroid.core.appBar.SmallTopAppBarProvider
import chooongg.libAndroid.core.widget.TopAppBarLayout

abstract class LibFragment : Fragment() {

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
    protected open fun getAppBarProvider(): AppBarProvider = SmallTopAppBarProvider()

    /**
     * 初始化布局
     */
    @LayoutRes
    protected abstract fun initLayout(): Int

    /**
     * 获取Toolbar的id
     */
    @IdRes
    protected open fun getToolbarId(): Int? = null

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
    ): View? = createContentViewByLib(container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
        initView(savedInstanceState)
        initContent(savedInstanceState)
        val toolbarId = getToolbarId()
        if (toolbarId != null){
            view.findViewById<Toolbar>(toolbarId)?.title = title
        }
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onCreateView"
        )
    }

    internal open fun getContentView(container: ViewGroup?): View? {
        return layoutInflater.inflate(initLayout(), container, false)
    }

    private fun createContentViewByLib(container: ViewGroup?): View? {
        return if (!attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)
            && javaClass.getAnnotation(AppBarEnable::class.java)?.value == true
        ) {
            getAppBarProvider().createLayout(null, this, getContentView(container))
        } else getContentView(container)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!isLoaded && !isHidden) {
            isLoaded = true
            initContentByLazy()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onStart"
        )
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            isLoaded = true
            initContentByLazy()
        }
        onBackPressedCallback.isEnabled = onBackPressedInterceptEnable
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onResume"
        )
    }

    override fun onPause() {
        super.onPause()
        onBackPressedCallback.isEnabled = false
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onPause"
        )
    }

    override fun onStop() {
        super.onStop()
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onStop"
        )
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

    private fun getToolbarByLib() = getToolbarId() ?: getAppBarProvider().getToolbarId()

    protected open fun onBackPressed() {}

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(
            "Fragment",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onDestroyView"
        )
    }
}