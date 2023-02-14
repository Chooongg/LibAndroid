package chooongg.libAndroid.basic.ext

import android.widget.Toast
import androidx.annotation.GravityInt
import androidx.annotation.StringRes
import chooongg.libAndroid.basic.ACTIVITY_STACK_TOP
import chooongg.libAndroid.basic.APPLICATION

/**
 * Toast 唯一实例
 */
private var basicToast: Toast? = null

/**
 * 展示 Toast
 */
fun showToast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    cancelToast()
    basicToast = Toast.makeText(ACTIVITY_STACK_TOP ?: APPLICATION, text, duration).apply {
        show()
    }
}

/**
 * 展示 Toast
 */
fun showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    cancelToast()
    basicToast = Toast.makeText(ACTIVITY_STACK_TOP ?: APPLICATION, resId, duration).apply {
        show()
    }
}

/**
 * 展示 Toast
 */
fun showToast(
    text: CharSequence?,
    @GravityInt gravity: Int,
    xOffset: Int = 0,
    yOffset: Int = 0,
    duration: Int = Toast.LENGTH_SHORT
) {
    cancelToast()
    basicToast = Toast.makeText(ACTIVITY_STACK_TOP ?: APPLICATION, text, duration).apply {
        setGravity(gravity, xOffset, yOffset)
        show()
    }
}

/**
 * 展示 Toast
 */
fun showToast(
    @StringRes resId: Int,
    @GravityInt gravity: Int,
    xOffset: Int = 0,
    yOffset: Int = 0,
    duration: Int = Toast.LENGTH_SHORT
) {
    cancelToast()
    basicToast = Toast.makeText(ACTIVITY_STACK_TOP ?: APPLICATION, resId, duration).apply {
        show()
    }
}


/**
 * 取消Toast
 */
fun cancelToast() {
    basicToast?.cancel()
    basicToast = null
}