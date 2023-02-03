package chooongg.libAndroid.basic.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * 获取Activity
 */
fun Context.getActivity(): Activity? {
    if (this is Activity) return this
    var context: Context? = this
    while (context != null) {
        if (context is Activity) return context
        context = (context as? ContextWrapper)?.baseContext
    }
    return null
}