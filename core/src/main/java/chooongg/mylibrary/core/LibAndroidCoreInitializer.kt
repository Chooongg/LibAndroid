package chooongg.mylibrary.core

import android.content.Context
import androidx.startup.Initializer
import chooongg.mylibrary.mmkv.MMKVInitializer

@Suppress("unused")
class LibAndroidCoreInitializer : Initializer<String> {
    override fun create(context: Context): String {

        return "LibAndroid core is initialized"
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf(MMKVInitializer::class.java)
}