package chooongg.libAndroid.basic.mmkv

import android.os.Parcelable
import chooongg.libAndroid.basic.mmkv.annotation.MMKVCryptKey
import chooongg.libAndroid.basic.mmkv.annotation.MMKVMapID
import chooongg.libAndroid.basic.mmkv.annotation.MMKVMode
import chooongg.libAndroid.basic.mmkv.annotation.MMKVRootPath
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

    val mmkv: MMKV by lazy {
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

    /**
     * 非空Boolean类型属性代理
     */
    fun mmkvBoolean(default: Boolean) =
        MMKVProperty(
            { mmkv.decodeBool(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Boolean类型属性代理
     */
    fun mmkvBooleanOrNull(default: Boolean?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeBool(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空Int类型属性代理
     */
    fun mmkvInt(default: Int) =
        MMKVProperty(
            { mmkv.decodeInt(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Int类型属性代理
     */
    fun mmkvIntOrNull(default: Int?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeInt(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空Long类型属性代理
     */
    fun mmkvLong(default: Long) =
        MMKVProperty(
            { mmkv.decodeLong(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Long类型属性代理
     */
    fun mmkvLongOrNull(default: Long?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeLong(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空Float类型属性代理
     */
    fun mmkvFloat(default: Float) =
        MMKVProperty(
            { mmkv.decodeFloat(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Float类型属性代理
     */
    fun mmkvFloatOrNull(default: Float?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeFloat(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空Double类型属性代理
     */
    fun mmkvDouble(default: Double) =
        MMKVProperty(
            { mmkv.decodeDouble(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Double类型属性代理
     */
    fun mmkvDoubleOrNull(default: Double?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeDouble(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空String类型属性代理
     */
    fun mmkvString(default: String) =
        MMKVProperty(
            { mmkv.decodeString(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空String类型属性代理
     */
    fun mmkvStringOrNull(default: String?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeString(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空Set<String>类型属性代理
     */
    fun mmkvStringSet(default: Set<String>) =
        MMKVProperty(
            { mmkv.decodeStringSet(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Set<String>类型属性代理
     */
    fun mmkvStringSetOrNull(default: Set<String>?) =
        MMKVProperty<Set<String>?>(
            { if (mmkv.containsKey(it)) mmkv.decodeStringSet(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空ByteArray类型属性代理
     */
    fun mmkvBytes(default: ByteArray) =
        MMKVProperty(
            { mmkv.decodeBytes(it, default) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空ByteArray类型属性代理
     */
    fun mmkvBytesOrNull(default: ByteArray?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeBytes(it) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    /**
     * 非空Parcelable类型属性代理
     */
    inline fun <reified T : Parcelable> mmkvParcelable(default: T) =
        MMKVProperty(
            { mmkv.decodeParcelable(it, T::class.java) },
            { key, value -> mmkv.encode(key, value) }
        )

    /**
     * 可空Parcelable类型属性代理
     */
    inline fun <reified T : Parcelable> mmkvParcelableOrNull(default: T?) =
        MMKVProperty(
            { if (mmkv.containsKey(it)) mmkv.decodeParcelable(it, T::class.java) else default },
            { key, value ->
                if (value == null) mmkv.removeValueForKey(key)
                else mmkv.encode(key, value)
            }
        )

    fun clear() {
        mmkv.clearAll()
    }
}