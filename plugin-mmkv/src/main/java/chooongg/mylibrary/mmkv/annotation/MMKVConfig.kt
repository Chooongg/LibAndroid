package chooongg.mylibrary.mmkv.annotation

import androidx.annotation.IntDef
import com.tencent.mmkv.MMKV

/**
 * 实例对象配置信息
 */
@Target(AnnotationTarget.CLASS)
annotation class MMKVConfig(

    /**
     * 实例 ID
     * @see MMKV.mmkvWithID
     */
    val mmapID: String,

    /**
     * 实例进程模式
     * @see MMKV.SINGLE_PROCESS_MODE
     * @see MMKV.MULTI_PROCESS_MODE
     */
    @MMKVMode val mode: Int = MMKV.SINGLE_PROCESS_MODE,

    /**
     * 实例的加密密钥 (不超过16字节)
     */
    val cryptKey: String = ""
) {
    @IntDef(MMKV.SINGLE_PROCESS_MODE, MMKV.MULTI_PROCESS_MODE)
    annotation class MMKVMode
}
