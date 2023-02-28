package chooongg.libAndroid.core.annotation

import java.lang.annotation.Inherited

@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class AppBarNavigationEnable(val value: Boolean = true)
