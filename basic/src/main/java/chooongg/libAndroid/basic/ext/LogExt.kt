package chooongg.libAndroid.basic.ext

import android.annotation.SuppressLint
import android.util.Log

private fun getTag(offset: Int): String {
    val stackTrace = Throwable().stackTrace
    val caller = when {
        3 + offset < 0 -> stackTrace[0]
        3 + offset > stackTrace.lastIndex -> stackTrace[stackTrace.lastIndex]
        else -> stackTrace[3 + offset]
    }
    return String.format(
        "\n at %s.%s(%s:%d)",
        caller.className,
        caller.methodName,
        caller.fileName,
        caller.lineNumber
    )
}

private fun getClassTag(clazz: Class<*>): String {
    return if (clazz.isAnnotationPresent(Metadata::class.java)) {
        String.format("\n at %s(%s.kt:%d)", clazz.name, clazz.simpleName, 0)
    } else String.format("\n at %s(%s.java:%d)", clazz.name, clazz.simpleName, 0)
}

fun logV(tag: String, msg: Any?, e: Throwable? = null, offsetStack: Int = 0) {
    if (BasicLog.isEnable) {
        if (e == null) Log.v(tag, "${msg}${getTag(offsetStack)}")
        else Log.v(tag, "${msg}${getTag(offsetStack)}", e)
    }
}

fun logVClass(tag: String, clazz: Class<*>, msg: Any?, e: Throwable? = null) {
    if (BasicLog.isEnable) if (e == null) Log.v(tag, "${msg}${getClassTag(clazz)}")
    else Log.v(tag, "${msg}${getClassTag(clazz)}", e)
}

fun logD(tag: String, msg: Any?, e: Throwable? = null, offsetStack: Int = 0) {
    if (BasicLog.isEnable) {
        if (e == null) Log.d(tag, "${msg}${getTag(offsetStack)}")
        else Log.d(tag, "${msg}${getTag(offsetStack)}", e)
    }
}

fun logDClass(tag: String, clazz: Class<*>, msg: Any?, e: Throwable? = null) {
    if (BasicLog.isEnable) if (e == null) Log.d(tag, "${msg}${getClassTag(clazz)}")
    else Log.d(tag, "${msg}${getClassTag(clazz)}", e)
}

fun logI(tag: String, msg: Any?, e: Throwable? = null, offsetStack: Int = 0) {
    if (BasicLog.isEnable) {
        if (e == null) Log.i(tag, "${msg}${getTag(offsetStack)}")
        else Log.i(tag, "${msg}${getTag(offsetStack)}", e)
    }
}

fun logIClass(tag: String, clazz: Class<*>, msg: Any?, e: Throwable? = null) {
    if (BasicLog.isEnable) if (e == null) Log.i(tag, "${msg}${getClassTag(clazz)}")
    else Log.i(tag, "${msg}${getClassTag(clazz)}", e)
}

fun logW(tag: String, msg: Any?, e: Throwable? = null, offsetStack: Int = 0) {
    if (BasicLog.isEnable) {
        if (e == null) Log.w(tag, "${msg}${getTag(offsetStack)}")
        else Log.w(tag, "${msg}${getTag(offsetStack)}", e)
    }
}

fun logWClass(tag: String, clazz: Class<*>, msg: Any?, e: Throwable? = null) {
    if (BasicLog.isEnable) if (e == null) Log.w(tag, "${msg}${getClassTag(clazz)}")
    else Log.w(tag, "${msg}${getClassTag(clazz)}", e)
}

fun logE(tag: String, msg: Any?, e: Throwable? = null, offsetStack: Int = 0) {
    if (BasicLog.isEnable) {
        if (e == null) Log.e(tag, "${msg}${getTag(offsetStack)}")
        else Log.e(tag, "${msg}${getTag(offsetStack)}", e)
    }
}

fun logEClass(tag: String, clazz: Class<*>, msg: Any?, e: Throwable? = null) {
    if (BasicLog.isEnable) if (e == null) Log.e(tag, "${msg}${getClassTag(clazz)}")
    else Log.e(tag, "${msg}${getClassTag(clazz)}", e)
}

@SuppressLint("NewApi")
fun logWTF(tag: String, msg: Any?, e: Throwable? = null, offsetStack: Int = 0) {
    if (BasicLog.isEnable) {
        if (e == null) Log.wtf(tag, "${msg}${getTag(offsetStack)}")
        else Log.wtf(tag, "${msg}${getTag(offsetStack)}", e)
    }
}

@SuppressLint("NewApi")
fun logWTFClass(tag: String, clazz: Class<*>, msg: Any?, e: Throwable? = null) {
    if (BasicLog.isEnable) if (e == null) Log.wtf(tag, "${msg}${getClassTag(clazz)}")
    else Log.wtf(tag, "${msg}${getClassTag(clazz)}", e)
}

object BasicLog {
    internal var isEnable = isAppDebug()

    fun setEnable(enable: Boolean) {
        isEnable = enable
    }
}