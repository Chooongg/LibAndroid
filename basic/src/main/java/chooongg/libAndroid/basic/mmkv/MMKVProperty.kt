package chooongg.libAndroid.basic.mmkv

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MMKVProperty<V> constructor(
    private val decode: (String) -> V,
    private val encode: (String, V) -> Unit
) : ReadWriteProperty<MMKVContainer, V> {

    override fun getValue(thisRef: MMKVContainer, property: KProperty<*>) = decode(property.name)
    override fun setValue(thisRef: MMKVContainer, property: KProperty<*>, value: V) {
        encode(property.name, value)
    }
}