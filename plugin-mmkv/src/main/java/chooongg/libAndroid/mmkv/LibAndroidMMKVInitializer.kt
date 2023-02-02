package chooongg.libAndroid.mmkv

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

@Suppress("unused")
class LibAndroidMMKVInitializer : Initializer<String> {
    override fun create(context: Context): String {
        return MMKV.initialize(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}