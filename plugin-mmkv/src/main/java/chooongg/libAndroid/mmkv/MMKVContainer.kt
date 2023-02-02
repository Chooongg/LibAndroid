package chooongg.libAndroid.mmkv

import chooongg.libAndroid.mmkv.annotation.MMKVCryptKey
import chooongg.libAndroid.mmkv.annotation.MMKVMapID
import chooongg.libAndroid.mmkv.annotation.MMKVMode
import chooongg.libAndroid.mmkv.annotation.MMKVRootPath
import com.tencent.mmkv.MMKV

/**
 * MMKV 键值容器
 *
 * 使用类注解标识容器信息
 * @see MMKVMapID
 * @see MMKVMode
 * @see MMKVCryptKey
 * @see MMKVRootPath
 */
abstract class MMKVContainer {

    val mmkv: MMKV? by lazy {
        val mmapID = javaClass.getAnnotation(MMKVMapID::class.java)?.id
        val mode = javaClass.getAnnotation(MMKVMode::class.java)?.mode ?: MMKV.SINGLE_PROCESS_MODE
        val cryptKey = javaClass.getAnnotation(MMKVCryptKey::class.java)?.key
        val rootPath = javaClass.getAnnotation(MMKVRootPath::class.java)?.path
        if (mmapID == null) {
            MMKV.defaultMMKV(mode, cryptKey)
        } else {
            MMKV.mmkvWithID(mmapID, mode, cryptKey, rootPath)
        }
    }

    fun clear() {
        mmkv?.clearAll()
    }
}