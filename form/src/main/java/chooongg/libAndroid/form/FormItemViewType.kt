package chooongg.libAndroid.form

import chooongg.libAndroid.form.item.FormItem
import chooongg.libAndroid.form.style.FormStyle
import chooongg.libAndroid.form.typeset.FormTypeset

/**
 * 表单项目类型
 */
data class FormItemViewType(
    val style: FormStyle,
    val typeset: FormTypeset?,
    val item: FormItem
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FormItemViewType) return false

        if (style != other.style) return false
        if (typeset != other.typeset) return false
        if (item::class != other.item::class) return false

        return true
    }

    override fun hashCode(): Int {
        var result = style.hashCode()
        result = 31 * result + typeset.hashCode()
        result = 31 * result + item::class.hashCode()
        return result
    }
}