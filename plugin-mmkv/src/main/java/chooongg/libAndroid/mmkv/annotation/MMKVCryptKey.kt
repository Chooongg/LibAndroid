package chooongg.libAndroid.mmkv.annotation

/**
 * MMKV 加密密钥
 */
@Target(AnnotationTarget.CLASS)
annotation class MMKVCryptKey(val key: String)
