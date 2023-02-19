package chooongg.libAndroid.form.style

import android.view.ViewGroup
import chooongg.libAndroid.form.item.FormItem
import chooongg.libAndroid.form.typeset.DefaultVerticalTypeset
import chooongg.libAndroid.form.typeset.FormTypeset

class DefaultStyle(typeset: FormTypeset = DefaultVerticalTypeset()) : FormStyle(typeset) {
    override fun onCreateItemParentLayout(parent: ViewGroup): ViewGroup? = null
    override fun onBindItemParentLayout(parent: ViewGroup, item: FormItem): ViewGroup = parent
}