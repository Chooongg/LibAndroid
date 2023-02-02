package chooongg.libAndroid.basic

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

@Suppress("unused")
class LibAndroidBasicInitializer : Initializer<String> {
    override fun create(context: Context): String {
        ApplicationManager.initialize(context as Application)
        setNightMode(LearnMMKV.DayNightMode.get())
        return "LibAndroid basic is initialized"
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}