package chooongg.mylibrary.mmkv

import chooongg.mylibrary.mmkv.annotation.MMKVConfig
import com.tencent.mmkv.MMKV

abstract class MMKVContainer {

    val mmkv by lazy {
        val mmkvConfig = (javaClass.getAnnotation(MMKVConfig::class.java)
            ?: throw RuntimeException("MMKVContainer must be annotated with @MMKVConfig"))
        MMKV.mmkvWithID(mmkvConfig.mmapID, mmkvConfig.mode, mmkvConfig.cryptKey)
    }
}