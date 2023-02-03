package chooongg.libAndroid.mmkv

import chooongg.libAndroid.basic.ext.getTClass
import com.tencent.mmkv.MMKV

open class MMKVKey<T>(
    private val mmkv: MMKV?,
    private val key: String,
    val defaultValue: T
) {

    @Suppress("UNCHECKED_CAST", "KotlinConstantConditions")
    open fun get(default: T = defaultValue): T {
        if (mmkv == null) return default
        return if (mmkv.containsKey(key)) {
            when (javaClass.getTClass()) {
                Int::class.javaObjectType -> mmkv.decodeInt(key) as T
                Long::class.javaObjectType -> mmkv.decodeLong(key) as T
                Float::class.javaObjectType -> mmkv.decodeFloat(key) as T
                Double::class.javaObjectType -> mmkv.decodeDouble(key) as T
                String::class.javaObjectType -> mmkv.decodeString(key) as T
                Boolean::class.javaObjectType -> mmkv.decodeBool(key) as T
                ByteArray::class.javaObjectType -> mmkv.decodeBytes(key) as T
                Set::class.javaObjectType -> {
                    val set = javaClass.getTClass() as Set<*>
                    if (set.javaClass.getTClass() == String::class.javaObjectType) {
                        mmkv.decodeStringSet(key) as T
                    } else throw RuntimeException("MMKV can't support the data type(Set<${set.javaClass.getTClass()}>)")
                }
                else -> throw RuntimeException("MMKV can't support the data type(${javaClass.getTClass().name})")
            }
        } else default
    }
}