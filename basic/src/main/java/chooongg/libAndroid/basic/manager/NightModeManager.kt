package chooongg.libAndroid.basic.manager

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import chooongg.libAndroid.basic.APPLICATION

object NightModeManager {

    private lateinit var sp: SharedPreferences

    internal fun initialize() {
        sp = APPLICATION.getSharedPreferences("LibAndroid", Context.MODE_PRIVATE)
        sp.getInt("NightMode", AppCompatDelegate.MODE_NIGHT_NO).let {
            AppCompatDelegate.setDefaultNightMode(it)
        }
    }

    fun getNightMode() = AppCompatDelegate.getDefaultNightMode()

    fun setNightMode(@NightMode mode: Int) {
        sp.edit().putInt("NightMode", mode).apply()
    }

    fun isNightMode() = when (APPLICATION.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        else -> false
    }

}