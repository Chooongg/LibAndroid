package chooongg.libAndroid.basic.ext

import java.lang.reflect.ParameterizedType

/**
 * 获取Class的第 N 项泛型类型
 * @param position 位置
 */
fun Class<*>.getGenericsClass(position: Int = 0): Class<*> {
    var genericSuperclass = genericSuperclass
    var superclass = superclass
    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {
            return genericSuperclass.actualTypeArguments[position] as Class<*>
        }
        genericSuperclass = superclass.genericSuperclass
        superclass = superclass.superclass
    }
    throw NullPointerException("没有找到泛型T")

}

/**
 * 获取Logcat的Class路径
 */
fun Class<*>.getLogcatPath() = if (isAnnotationPresent(Metadata::class.java)) {
    String.format("%s(%s.kt:%d)", name, simpleName, 0)
} else String.format("%s(%s.java:%d)", name, simpleName, 0)