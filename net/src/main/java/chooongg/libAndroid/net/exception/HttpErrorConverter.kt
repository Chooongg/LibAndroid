package chooongg.libAndroid.net.exception

import chooongg.libAndroid.basic.ext.isAppDebug

/**
 * 错误类型转换成语义化文字
 */
abstract class HttpErrorConverter {

    abstract fun convertRelease(type: HttpException.Type): String
    abstract fun convertDebug(type: HttpException.Type): String


    fun convert(type: HttpException.Type) =
        if (isAppDebug()) convertDebug(type) else convertRelease(type)
}