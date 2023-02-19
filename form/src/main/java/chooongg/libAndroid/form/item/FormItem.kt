package chooongg.libAndroid.form.item

import android.view.View
import android.view.ViewGroup
import chooongg.libAndroid.form.FormAdapter
import chooongg.libAndroid.form.FormViewHolder
import chooongg.libAndroid.form.enum.FormEnableMode
import chooongg.libAndroid.form.enum.FormOutputMode
import chooongg.libAndroid.form.enum.FormVisibilityMode
import chooongg.libAndroid.form.typeset.FormTypeset
import kotlin.reflect.KClass

abstract class FormItem(
    /**
     * 名称
     */
    val name: CharSequence,
    /**
     * 字段
     */
    val field: String?
) {

    /**
     * 提示
     */
    open var hint: CharSequence? = null

    /**
     * 内容
     */
    open var content: CharSequence? = null

    /**
     * 扩展字段和内容
     */
    private var extensionFieldAndContent: HashMap<String, CharSequence?>? = null

    /**
     * 查看时类型
     */
    open var viewingType: KClass<out FormItem> = this::class
        protected set

    /**
     * 是否展示
     */
    var isToDisplay = true

    /**
     * 是否启用
     */
    var isEnable = true

    /**
     * 是否必填
     */
    var isRequired: Boolean = false

    /**
     * 是否忽略名称的EMS
     */
    open var isIgnoreNameEms: Boolean = false

    /**
     * 是否在组边缘展示
     */
    open var isOnEdgeToDisplay = true

    open var issef = true

    /**
     * 可见模式
     */
    open var visibilityMode: FormVisibilityMode = FormVisibilityMode.ALWAYS

    /**
     * 启用模式
     */
    open var enableMode: FormEnableMode = FormEnableMode.ONLY_EDIT

    /**
     * 输出模式
     */
    open var outputMode: FormOutputMode = FormOutputMode.ALWAYS


    open var isNeedTypeset: Boolean = true

    /**
     * 排版样式
     */
    open var typeset: FormTypeset? = null

    /**
     * 获取扩展哈希映射
     */
    protected fun getExtensionFieldAndContent() = if (extensionFieldAndContent == null) {
        extensionFieldAndContent = HashMap()
        extensionFieldAndContent!!
    } else extensionFieldAndContent!!

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    /**
     * 真实的可见性
     */
    open fun isRealVisible(globalAdapter:FormAdapter):Boolean{
        if (!isToDisplay) return false
        return when(visibilityMode) {
            FormVisibilityMode.ALWAYS -> true
            FormVisibilityMode.ONLY_EDIT -> globalAdapter.isEditable
            FormVisibilityMode.ONLY_SEE -> !globalAdapter.isEditable
            FormVisibilityMode.NEVER-> false
        }
    }

    open fun isRealEnable(globalAdapter:FormAdapter):Boolean{
        if (!isEnable) return false
        return when(enableMode) {
            FormEnableMode.ALWAYS -> true
            FormEnableMode.ONLY_EDIT -> globalAdapter.isEditable
            FormEnableMode.ONLY_SEE -> !globalAdapter.isEditable
            FormEnableMode.NEVER-> false
        }
    }

    abstract fun onCreateItemView(parent: ViewGroup): View

    abstract fun onBindItemView(holder: FormViewHolder, payloads: MutableList<Any>?)
}