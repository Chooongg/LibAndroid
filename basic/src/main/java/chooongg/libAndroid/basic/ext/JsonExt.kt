package chooongg.libAndroid.basic.ext

import org.json.JSONArray
import org.json.JSONObject

/**
 * 用于构建JSONObject
 */
fun json(block: JSONObjectBuilder.() -> Unit): JSONObject {
    return JSONObjectBuilder().apply(block).json
}

class JSONObjectBuilder {
    internal val json: JSONObject = JSONObject()

    /**
     * KeyValue中缀函数
     */
    infix fun String.to(value: Any?) {
        if (value.isValidDataType()) json.put(this, value ?: JSONObject.NULL)
        else throw IllegalArgumentException("$value is not a valid data type")
    }
}

/**
 * 构建JSONArray
 */
fun jsonArray(vararg values: Any?): JSONArray {
    val jsonArray = JSONArray()
    for (value in values) {
        if (value.isValidDataType()) jsonArray.put(value ?: JSONObject.NULL)
        else throw IllegalArgumentException("$value is not a valid data type")
    }
    return jsonArray
}

private fun Any?.isValidDataType() = when (this) {
    is String, is Number, is Boolean, is JSONObject, is JSONArray -> true
    null -> true
    else -> false
}