package chooongg.libAndroid.core.ext

import androidx.lifecycle.ViewModel
import chooongg.libAndroid.basic.LibAndroidException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
fun <M : ViewModel> getViewModelClass(any: Any): Class<M> {
    var genericSuperclass = any.javaClass.genericSuperclass
    var superclass = any.javaClass.superclass
    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {
            genericSuperclass.actualTypeArguments.forEach {
                try {
                    return it as Class<M>
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: ClassCastException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            }
        }
        genericSuperclass = superclass.genericSuperclass
        superclass = superclass.superclass
    }
    throw LibAndroidException("没有找到ViewModel泛型")
}