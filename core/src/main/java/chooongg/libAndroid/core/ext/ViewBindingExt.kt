package chooongg.libAndroid.core.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.basic.ext.getGenericsClass

@Suppress("UNCHECKED_CAST")
fun <B : ViewBinding> AppCompatActivity.getBindingT(index: Int): B {
    val clazz = javaClass.getGenericsClass(index) as Class<B>
    val method = clazz.getMethod("inflate", LayoutInflater::class.java)
    val b = method.invoke(null, LayoutInflater.from(this)) as B
    (b as? ViewDataBinding)?.lifecycleOwner = this
    return b
}

@Suppress("UNCHECKED_CAST")
fun <B : ViewBinding> Fragment.getBindingT(
    index: Int,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    attachToParent: Boolean
): B {
    val clazz = javaClass.getGenericsClass(index) as Class<B>
    val b = clazz.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    ).invoke(null, layoutInflater, parent, attachToParent) as B
    (b as? ViewDataBinding)?.lifecycleOwner = viewLifecycleOwner
    return b
}

@Suppress("UNCHECKED_CAST")
inline fun <reified B : ViewBinding> ViewStub.inflateToBinding() =
    B::class.java.getMethod("bind", View::class.java).invoke(null, inflate()) as B