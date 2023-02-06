package chooongg.libAndroid.basic

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import chooongg.libAndroid.basic.manager.ApplicationManager
import chooongg.libAndroid.basic.manager.NightModeManager
import com.tencent.mmkv.MMKV

@Suppress("unused")
class LibAndroidBasicInitializer : Initializer<String> {
    override fun create(context: Context): String {
        ApplicationManager.initialize(context as Application)
        MMKV.initialize(context)
        NightModeManager.initialize()
        return "LibAndroid basic is initialized"
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}