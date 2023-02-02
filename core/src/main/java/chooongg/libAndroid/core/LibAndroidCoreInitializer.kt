package chooongg.libAndroid.core

import android.content.Context
import androidx.startup.Initializer
import chooongg.libAndroid.mmkv.LibAndroidMMKVInitializer
import com.facebook.stetho.Stetho

@Suppress("unused")
class LibAndroidCoreInitializer : Initializer<String> {
    override fun create(context: Context): String {
        Stetho.initializeWithDefaults(context)
        return "LibAndroid core is initialized"
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf(LibAndroidMMKVInitializer::class.java)
}