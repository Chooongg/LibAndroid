package chooongg.libAndroid.basic.ext

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ApplicationInfoFlags
import android.os.Build
import chooongg.libAndroid.basic.APPLICATION

/**
 * 判断是否是Debug版本
 *
 * @param packageName 包名
 */
@Suppress("DEPRECATION")
fun isAppDebug(packageName: String = APPLICATION.packageName): Boolean {
    if (packageName.isEmpty()) return false
    return try {
        val ai = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            APPLICATION.packageManager.getApplicationInfo(packageName, ApplicationInfoFlags.of(0))
        } else APPLICATION.packageManager.getApplicationInfo(packageName, 0)
        ai.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        false
    }
}

/**
 * Debug代码块
 */
fun debug(init: () -> Unit) = if (isAppDebug()) init() else Unit

/**
 * Release代码块
 */
fun release(init: () -> Unit) = if (!isAppDebug()) init() else Unit