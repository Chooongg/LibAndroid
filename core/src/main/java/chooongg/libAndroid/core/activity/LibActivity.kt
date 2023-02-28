package chooongg.libAndroid.core.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import chooongg.libAndroid.basic.ext.BasicLog
import chooongg.libAndroid.basic.ext.attrBoolean
import chooongg.libAndroid.basic.ext.getLogcatPath
import chooongg.libAndroid.core.R
import chooongg.libAndroid.core.annotation.ActivityTransitions
import chooongg.libAndroid.core.annotation.AppBarEnable
import chooongg.libAndroid.core.annotation.AppBarNavigationEnable
import chooongg.libAndroid.core.annotation.EdgeToEdge
import chooongg.libAndroid.core.appBar.AppBarDelegate
import chooongg.libAndroid.core.appBar.TopSmallAppBarDelegate
import chooongg.libAndroid.core.ext.EXTRA_TRANSITION_NAME
import chooongg.libAndroid.core.ext.TRANSITION_NAME_CONTAINER_TRANSFORM
import com.google.android.material.motion.MotionUtils
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialSharedAxis

@EdgeToEdge(true)
abstract class LibActivity : AppCompatActivity(), LifecycleEventObserver {

    inline val context: Context get() = this
    inline val activity: LibActivity get() = this

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
     * 初始化视图
     */
    protected open fun initView(savedInstanceState: Bundle?) {}

    /**
     * 初始化内容
     */
    protected open fun initContent(savedInstanceState: Bundle?) {}

    /**
     * 刷新时
     */
    open fun onRefresh(any: Any? = null) {}

    final override fun onCreate(savedInstanceState: Bundle?) {
        createTransitions()
        super.onCreate(savedInstanceState)
        if (BasicLog.isEnable) lifecycle.addObserver(this)
        createEdgeToEdge()
        createContentViewByLib()
        initView(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initContent(savedInstanceState)
    }

    protected open fun createTransitions() {
        if (javaClass.getAnnotation(ActivityTransitions::class.java)?.enable != true) return
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            sharedElementsUseOverlay = false
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, true)
            exitTransition = null
            returnTransition = MaterialSharedAxis(MaterialSharedAxis.Y, false)
            reenterTransition = null
            transitionBackgroundFadeDuration = MotionUtils.resolveThemeDuration(
                context, com.google.android.material.R.attr.motionDurationLong1, -1
            ).toLong()
        }
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        val transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)
        if (transitionName == TRANSITION_NAME_CONTAINER_TRANSFORM) {
            findViewById<FrameLayout>(android.R.id.content).transitionName = transitionName
            setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
            window.sharedElementEnterTransition = buildContainerTransform(true)
            window.sharedElementReturnTransition = buildContainerTransform(false)
        }
    }

    protected open fun buildContainerTransform(entering: Boolean): MaterialContainerTransform {
        val transform = MaterialContainerTransform(this, entering)
        transform.addTarget(android.R.id.content)
        transform.fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
        transform.pathMotion = MaterialArcMotion()
        return transform
    }

    private fun createEdgeToEdge() {
        if (attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) return
        javaClass.getAnnotation(EdgeToEdge::class.java)?.let {
            if (!it.isEdgeToEdge) return
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }

    private fun createContentViewByLib() {
        if (!attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)
            && javaClass.getAnnotation(AppBarEnable::class.java)?.value == true
        ) setContentView(getAppBarDelegate().createLayout(this, null, null, getContentView()))
        else setContentView(getContentView())
        if (javaClass.getAnnotation(AppBarNavigationEnable::class.java)?.value != false) {
            supportActionBar?.also {
                it.setHomeButtonEnabled(true)
                it.setDisplayHomeAsUpEnabled(true)
                it.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = super.onOptionsItemSelected(item)
        return if (!result && item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        } else result
    }

    /**
     * 自动创建内容视图
     */
    internal open fun getContentView(): View {
        return layoutInflater.inflate(initLayout(), null)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(this)
    }

    final override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} ${event.name}"
        )
    }
}