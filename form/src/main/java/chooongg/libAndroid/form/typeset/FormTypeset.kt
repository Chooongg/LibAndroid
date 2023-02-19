package chooongg.libAndroid.form.typeset

import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.GravityInt
import chooongg.libAndroid.form.item.FormItem

abstract class FormTypeset(@GravityInt val contentGravity:Int) {

    /**
     * 创建项目的排版布局
     */
    abstract fun onCreateItemTypesetLayout(parent: ViewGroup): ViewGroup


    /**
     * 绑定项目的排版布局
     * @return 排版布局
     */
    abstract fun onBindItemTypesetLayout(parent: ViewGroup, item: FormItem)
}