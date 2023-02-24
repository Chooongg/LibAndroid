package chooongg.libAndroid.form.item

import android.view.View
import android.view.ViewGroup
import chooongg.libAndroid.form.FormViewHolder

class FormGroupTitle(name: CharSequence) : FormItem(name, null) {

    override var isNeedTypeset: Boolean = false

    override fun onCreateItemView(parent: ViewGroup): View {
        return View(parent.context)
    }

    override fun onBindItemView(holder: FormViewHolder, payloads: MutableList<Any>?) {

    }
}