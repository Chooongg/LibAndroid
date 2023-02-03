package chooongg.libAndroid.basic.ext

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.widget.ContentFrameLayout

/**
 * 获取Activity标签
 */
@Suppress("DEPRECATION")
fun Context.loadActivityLabel(): CharSequence {
    val activity = getActivity() ?: return ""
    val activityInfo = ComponentName(activity, activity.javaClass).let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getActivityInfo(it, PackageManager.ComponentInfoFlags.of(0))
        } else packageManager.getActivityInfo(it, 0)
    }
    return activityInfo.loadLabel(packageManager)
}

/**
 * 软键盘显示监听
 */
fun Activity.onKeyboardShowListener(listener: (isShow: Boolean) -> Unit) {
    findViewById<ContentFrameLayout>(android.R.id.content).viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = window.decorView.rootView.height
        val heightDifference = screenHeight - rect.bottom
        if (heightDifference > screenHeight / 3) {
            listener.invoke(true)
        } else {
            listener.invoke(false)
        }
    }
}