package chooongg.libAndroid.mmkv.annotation

import com.tencent.mmkv.MMKV

/**
 * MMKV 模式
 *
 * @see MMKV.SINGLE_PROCESS_MODE
 * @see MMKV.MULTI_PROCESS_MODE
 */
@Target(AnnotationTarget.CLASS)
annotation class MMKVMode(val mode: Int) {
}
