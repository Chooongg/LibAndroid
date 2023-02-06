package chooongg.libAndroid.core.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import chooongg.libAndroid.basic.ext.attrBoolean
import chooongg.libAndroid.core.annotation.EdgeToEdge
import chooongg.libAndroid.core.annotation.Title

abstract class LibActivity : AppCompatActivity() {

    inline val context: Context get() = this
    inline val activity: LibActivity get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEdgeToEdge()
        initTitle()
    }


    protected open fun initTitle() {
        if (javaClass.isAnnotationPresent(Title::class.java)) {
            title = javaClass.getAnnotation(Title::class.java)!!.value
        }
    }

    protected open fun initEdgeToEdge() {
        if (attrBoolean(androidx.appcompat.R.attr.windowActionBar, false)) return
        javaClass.getAnnotation(EdgeToEdge::class.java)?.let {
            if (!it.isEdgeToEdge) return
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
}