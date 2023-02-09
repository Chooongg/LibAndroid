package chooongg.libAndroid.net

import chooongg.libAndroid.basic.ext.withIO
import chooongg.libAndroid.net.exception.HttpException
import chooongg.libAndroid.net.exception.HttpRetryException

/**
 * 网络同步请求且返回提封装 DSL
 */
open class CoroutinesRequestSyncDSL<RESPONSE : ResponseData<DATA>, DATA> {

    private var api: (suspend () -> RESPONSE)? = null

    fun api(block: suspend () -> RESPONSE) {
        api = block
    }

    internal suspend fun executeRequest(): DATA? {
        if (api == null) {
            throw NullPointerException("HttpRequest not implemented api method, cancel operation.")
        }
        return withIO {
            try {
                val response = api!!.invoke()
                response.checkData()
            } catch (e: HttpRetryException) {
                executeRequest()
            } catch (e: Exception) {
                throw HttpException(e)
            }
        }
    }
}