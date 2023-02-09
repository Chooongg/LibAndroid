package chooongg.libAndroid.core.viewBinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import chooongg.libAndroid.core.R
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
object ViewBindingUtils {

    @JvmStatic
    fun <BINDING : ViewBinding> inflateWithGeneric(
        genericOwner: Any,
        layoutInflater: LayoutInflater
    ): BINDING =
        withGenericBindingClass(genericOwner) { clazz ->
            clazz.getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, layoutInflater) as BINDING
        }.withLifecycleOwner(genericOwner)

    @JvmStatic
    fun <BINDING : ViewBinding> inflateWithGeneric(genericOwner: Any, parent: ViewGroup): BINDING =
        inflateWithGeneric(genericOwner, LayoutInflater.from(parent.context), parent, false)

    @JvmStatic
    fun <BINDING : ViewBinding> inflateWithGeneric(
        genericOwner: Any,
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean
    ): BINDING =
        withGenericBindingClass(genericOwner) { clazz ->
            clazz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
                .invoke(null, layoutInflater, parent, attachToParent) as BINDING
        }.withLifecycleOwner(genericOwner)

    @JvmStatic
    @Suppress("DEPRECATION")
    fun <BINDING : ViewBinding> getBindingWithGeneric(genericOwner: Any, view: View): BINDING =
        view.getTag(R.id.tag_view_binding) as? BINDING ?: bindWithGeneric<BINDING>(
            genericOwner,
            view
        )
            .also { view.setTag(R.id.tag_view_binding, it) }

    @JvmStatic
    @Deprecated(
        "Use ViewBindingUtil.getBindingWithGeneric<VB>(genericOwner, view) instead.",
        ReplaceWith("ViewBindingUtil.getBindingWithGeneric<VB>(genericOwner, view)")
    )
    fun <BINDING : ViewBinding> bindWithGeneric(genericOwner: Any, view: View): BINDING =
        withGenericBindingClass(genericOwner) { clazz ->
            clazz.getMethod("bind", View::class.java).invoke(null, view) as BINDING
        }.withLifecycleOwner(genericOwner)

    private fun <BINDING : ViewBinding> BINDING.withLifecycleOwner(genericOwner: Any) = apply {
        if (this is ViewDataBinding && genericOwner is ComponentActivity) {
            lifecycleOwner = genericOwner
        } else if (this is ViewDataBinding && genericOwner is Fragment) {
            lifecycleOwner = genericOwner.viewLifecycleOwner
        }
    }

    private fun <BINDING : ViewBinding> withGenericBindingClass(
        genericOwner: Any,
        block: (Class<BINDING>) -> BINDING
    ): BINDING {
        var genericSuperclass = genericOwner.javaClass.genericSuperclass
        var superclass = genericOwner.javaClass.superclass
        while (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                genericSuperclass.actualTypeArguments.forEach {
                    try {
                        return block.invoke(it as Class<BINDING>)
                    } catch (_: NoSuchMethodException) {
                    } catch (_: ClassCastException) {
                    } catch (e: InvocationTargetException) {
                        var tagException: Throwable? = e
                        while (tagException is InvocationTargetException) {
                            tagException = e.cause
                        }
                        throw tagException
                            ?: IllegalArgumentException("ViewBinding generic was found, but creation failed.")
                    }
                }
            }
            genericSuperclass = superclass.genericSuperclass
            superclass = superclass.superclass
        }
        throw IllegalArgumentException("There is no generic of ViewBinding.")
    }
}