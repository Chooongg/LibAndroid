package chooongg.libAndroid.form.style

import android.view.ViewGroup
import chooongg.libAndroid.form.item.FormItem
import chooongg.libAndroid.form.typeset.FormTypeset

abstract class FormStyle(val defaultTypeset: FormTypeset) {

    /**
     * 创建项目的父布局
     */
    abstract fun onCreateItemParentLayout(parent: ViewGroup): ViewGroup?

    /**
     * 绑定项目的父布局
     * @return 创建的父布局，如果没有创建则返回项目父布局
     */
    abstract fun onBindItemParentLayout(parent: ViewGroup, item: FormItem): ViewGroup

}