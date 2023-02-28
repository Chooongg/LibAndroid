package chooongg.libAndroid.core.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.R
import com.google.android.material.appbar.MaterialToolbar

@SuppressLint("RestrictedApi")
class TopAppBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.toolbarStyle
) : MaterialToolbar(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        private val DEF_STYLE_RES: Int = R.style.Widget_Material3_Toolbar
    }

    init {
//        applyWindowInsets()
    }

    private fun applyWindowInsets() {
        val minHeight = minimumHeight
        val relativePaddingTop = paddingTop
        val relativePaddingBottom = paddingBottom
        val relativePaddingLeft = paddingLeft
        val relativePaddingRight = paddingRight
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val inset =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.setPadding(
                relativePaddingLeft + inset.left,
                relativePaddingTop + inset.top,
                relativePaddingRight + inset.right,
                relativePaddingBottom + inset.bottom
            )
            v.minimumHeight = minHeight + inset.top
            insets
        }
    }
}