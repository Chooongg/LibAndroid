package chooongg.libAndroid.core.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import chooongg.libAndroid.basic.ext.attrBoolean
import chooongg.libAndroid.basic.ext.getLogcatPath
import chooongg.libAndroid.core.annotation.ActivityTransitions
import chooongg.libAndroid.core.annotation.EdgeToEdge
import chooongg.libAndroid.core.annotation.Title
import chooongg.libAndroid.core.ext.EXTRA_TRANSITION_NAME
import chooongg.libAndroid.core.ext.TRANSITION_NAME_CONTAINER_TRANSFORM
import com.google.android.material.motion.MotionUtils
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialSharedAxis

@EdgeToEdge(true)
abstract class LibActivity : AppCompatActivity() {

    inline val context: Context get() = this
    inline val activity: LibActivity get() = this

    @LayoutRes
    protected abstract fun initLayout(): Int

    protected open fun initView(savedInstanceState: Bundle?) {}

    protected open fun initContent(savedInstanceState: Bundle?) {}

    open fun onRefresh(any: Any? = null) {}

    final override fun onCreate(savedInstanceState: Bundle?) {
        onCreateTransitions()
        super.onCreate(savedInstanceState)
        onCreateTitle()
        onCreateEdgeToEdge()
        autoSetContentView()

        initView(savedInstanceState)
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onCreateView"
        )
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initContent(savedInstanceState)
    }

    protected open fun onCreateTransitions() {
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

    private fun onCreateTitle() {
        if (javaClass.isAnnotationPresent(Title::class.java)) {
            title = javaClass.getAnnotation(Title::class.java)!!.value
        }
    }

    private fun onCreateEdgeToEdge() {
        if (attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) return
        javaClass.getAnnotation(EdgeToEdge::class.java)?.let {
            if (!it.isEdgeToEdge) return
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 自动创建内容视图
     */
    protected open fun autoSetContentView() {
        setContentView(initLayout())
    }

    /**
     * 创建顶部栏
     * @return Triple<总布局, 添加内容的父布局, 添加内容子项的位置>
     */
    protected open fun onCreateAppBarParent(): Triple<ViewGroup, ViewGroup, Int>? = null

    override fun setContentView(layoutResID: Int) {
        setContentView(layoutInflater.inflate(layoutResID, null))
    }

    override fun setContentView(view: View?) {
        if (!attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) {
            val appBarParent = onCreateAppBarParent()
            if (appBarParent != null) {
                if (view != null) appBarParent.second.addView(view, appBarParent.third)
                super.setContentView(appBarParent.first)
            } else super.setContentView(view)
        } else super.setContentView(view)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        if (!attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) {
            val appBarParent = onCreateAppBarParent()
            if (appBarParent != null) {
                if (view != null) appBarParent.second.addView(view, appBarParent.third, params)
                super.setContentView(appBarParent.first)
            } else super.setContentView(view)
        } else super.setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onStart"
        )
    }

    override fun onResume() {
        super.onResume()
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onResume"
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onPause"
        )
    }

    override fun onStop() {
        super.onStop()
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onStop"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(
            "Activity",
            "${javaClass.getLogcatPath()}${if (title.isNullOrEmpty()) "" else " $title"} onDestroy"
        )
    }
}