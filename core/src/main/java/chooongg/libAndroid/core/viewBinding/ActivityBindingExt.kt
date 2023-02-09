package chooongg.libAndroid.core.viewBinding

import android.app.Activity
import androidx.viewbinding.ViewBinding

interface ActivityBinding<BINDING : ViewBinding> {
    val binding: BINDING
    fun Activity.setContentViewWithBinding()
    fun Activity.removeBinding()
}

class ActivityBindingDelegate<BINDING : ViewBinding> :
    ActivityBinding<BINDING> {

    private var _binding: BINDING? = null

    override val binding: BINDING get() = _binding!!

    override fun Activity.setContentViewWithBinding() {
        _binding = ViewBindingUtils.inflateWithGeneric<BINDING>(this, layoutInflater).apply {
            setContentView(root)
        }
    }

    override fun Activity.removeBinding() {
        _binding = null
    }
}