package chooongg.libAndroid.core.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import chooongg.libAndroid.basic.ext.attrBoolean
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

        onCreateTopAppBar()
        onCreateContentView()
        initView(savedInstanceState)
        Log.d("Activity", "[${javaClass.simpleName}][${title ?: "UNDEFINE"}] onCreateView")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initContent(savedInstanceState)
    }

    protected open fun onCreateContentView() {
        setContentView(initLayout())
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

    protected open fun onCreateTopAppBar() {
        if (attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) return
    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity", "[${javaClass.simpleName}][${title ?: "UNDEFINE"}] onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity", "[${javaClass.simpleName}][${title ?: "UNDEFINE"}] onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity", "[${javaClass.simpleName}][${title ?: "UNDEFINE"}] onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity", "[${javaClass.simpleName}][${title ?: "UNDEFINE"}] onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity", "[${javaClass.simpleName}][${title ?: "UNDEFINE"}] onDestroy")
    }
}