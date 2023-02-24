package chooongg.libAndroid.form.cteator

import chooongg.libAndroid.form.item.FormItem

/**
 * 表单片段创建器
 */
class FormPartCreator {

    internal val partList = mutableListOf<MutableList<out FormItem>>()

    /**
     * 片段名称
     */
    var partName: CharSequence? = null

    /**
     * 片段字段
     */
    var partField: String? = null

    /**
     * 是否是动态片段
     */
    var dynamicPart: Boolean = false

    /**
     * 动态片段删除确认
     */
    var dynamicPartDeleteConfirm: Boolean = true

    @androidx.annotation.IntRange(from = 0)
    var dynamicMinGroupCount: Int = 1

    @androidx.annotation.IntRange(from = 1)
    var dynamicMaxGroupCount: Int = Int.MAX_VALUE

    internal var dynamicPartCreateGroupBlock: (FormGroupCreator.() -> Unit)? = null

    internal var dynamicPartNameFormatBlock: ((name: CharSequence?, index: Int) -> CharSequence)? =
        null

    /**
     * 添加组
     */
    fun createGroup(block: FormGroupCreator.() -> Unit) {
        partList.add(FormGroupCreator().apply(block).groupList)
    }

    /**
     * 动态片段添加组
     */
    fun dynamicPartCreateGroupListener(block: (FormGroupCreator.() -> Unit)?) {
        dynamicPartCreateGroupBlock = block
        if (block != null) {
            dynamicPart = true
        }
    }

    /**
     * 动态片段名称格式化
     */
    fun dynamicPartNameFormatListener(block: ((name: CharSequence?, index: Int) -> CharSequence)?) {
        dynamicPartNameFormatBlock = block
    }
}