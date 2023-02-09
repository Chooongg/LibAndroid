package chooongg.libAndroid.net.ext

import chooongg.libAndroid.net.CoroutinesRequestSyncBasicDSL
import chooongg.libAndroid.net.CoroutinesRequestSyncDSL
import chooongg.libAndroid.net.ResponseData

/**
 * 常规同步请求 DSL
 */
suspend fun <DATA> requestSync(block: suspend CoroutinesRequestSyncDSL<ResponseData<DATA>, DATA>.() -> Unit) =
    CoroutinesRequestSyncDSL<ResponseData<DATA>, DATA>().apply { block(this) }.executeRequest()

/**
 * 基础的同步请求 DSL
 */
suspend fun <RESPONSE> requestBasicSync(block: suspend CoroutinesRequestSyncBasicDSL<RESPONSE>.() -> Unit) =
    CoroutinesRequestSyncBasicDSL<RESPONSE>().apply { block(this) }.executeRequest()