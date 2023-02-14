package chooongg.libAndroid.core.ext

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText

/**
 * 添加过滤器
 */
fun EditText.addFilter(filter: InputFilter) {
    val list = filters.toMutableList()
    list.add(filter)
    filters = list.toTypedArray()
}