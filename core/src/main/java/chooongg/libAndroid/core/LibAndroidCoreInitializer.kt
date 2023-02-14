package chooongg.libAndroid.core

import android.content.Context
import androidx.startup.Initializer
import chooongg.libAndroid.basic.LibAndroidBasicInitializer
import chooongg.libAndroid.core.permission.PermissionInterceptor
import com.facebook.stetho.Stetho
import com.hjq.permissions.XXPermissions

@Suppress("unused")
class LibAndroidCoreInitializer : Initializer<String> {
    override fun create(context: Context): String {
        XXPermissions.setInterceptor(PermissionInterceptor())
        Stetho.initializeWithDefaults(context)
        return "LibAndroid core is initialized"
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf(LibAndroidBasicInitializer::class.java)
}