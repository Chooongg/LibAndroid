package chooongg.libAndroid.core.widget.autoSize

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

@Suppress("LeakingThis")
open class AutoSizeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialTextView(context, attrs, defStyleAttr) {

    val autoSizeHelper = AutoSizeTextHelper.create(this, attrs, defStyleAttr)

    fun requestAutoSize() {
        if (autoSizeHelper.isEnabled) autoSizeHelper.autoSize()
    }
}