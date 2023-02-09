package chooongg.libAndroid.core.viewBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * 填充绑定视图
 * @param inflater 布局填充器
 */
inline fun <reified BINDING : ViewBinding> inflateBinding(inflater: LayoutInflater) =
    BINDING::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, inflater) as BINDING

/**
 * 填充绑定视图
 * @param parent 父视图
 */
inline fun <reified BINDING : ViewBinding> inflateBinding(parent: ViewGroup): BINDING =
    inflateBinding(LayoutInflater.from(parent.context), parent, false)

/**
 * 填充绑定视图
 * @param inflater 布局填充器
 * @param parent 父视图
 * @param attachToParent 是否附加到父视图
 */
inline fun <reified BINDING : ViewBinding> inflateBinding(
    inflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean
) = BINDING::class.java.getMethod(
    "inflate",
    LayoutInflater::class.java,
    ViewGroup::class.java,
    Boolean::class.java
).invoke(null, inflater, parent, attachToParent) as BINDING