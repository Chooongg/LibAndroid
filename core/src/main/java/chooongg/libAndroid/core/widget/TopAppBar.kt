package chooongg.libAndroid.core.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.MaterialToolbar

class TopAppBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.toolbarStyle
) : MaterialToolbar(context, attrs, defStyleAttr)