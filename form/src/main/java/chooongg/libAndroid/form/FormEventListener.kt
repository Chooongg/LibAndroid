package chooongg.libAndroid.form

import android.view.View
import chooongg.libAndroid.form.item.FormItem

interface FormEventListener {
    /**
     * 表单点击事件
     */
    fun onFormClick(manager: FormAdapter, item: FormItem, view: View, position: Int) {}

    /**
     * 表单菜单点击事件
     */
    fun onFormMenuClick(manager: FormAdapter, item: FormItem, view: View, position: Int) {}

    /**
     * 表单内容更改事件
     */
    fun onFormContentChanged(manager: FormAdapter, item: FormItem, position: Int) {}
}