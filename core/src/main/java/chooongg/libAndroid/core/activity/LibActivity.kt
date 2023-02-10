package chooongg.libAndroid.core.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import chooongg.libAndroid.basic.ext.attrBoolean
import chooongg.libAndroid.basic.ext.logDClass
import chooongg.libAndroid.core.annotation.EdgeToEdge
import chooongg.libAndroid.core.annotation.Title
import chooongg.libAndroid.core.fragment.LibFragment

abstract class LibActivity : AppCompatActivity() {

    inline val context: Context get() = this
    inline val activity: LibActivity get() = this

    @LayoutRes
    protected abstract fun initLayout(): Int

    protected open fun initView(savedInstanceState: Bundle?) {}

    protected open fun initContent(savedInstanceState: Bundle?) {}

    open fun onRefresh(any: Any? = null) {}

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateTitle()
        onCreateEdgeToEdge()

        onCreateTopAppBar()
        onCreateContentView()
        initView(savedInstanceState)
        logDClass("Activity", javaClass, "---------- $title ---------- onCreate")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initContent(savedInstanceState)
    }

    protected open fun onCreateContentView() {
        setContentView(initLayout())
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
        }
    }

    protected open fun onCreateTopAppBar() {
        if (attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) return
    }

    override fun onDestroy() {
        super.onDestroy()
        logDClass("Activity", javaClass, "---------- $title ---------- onDestroy")
    }
}