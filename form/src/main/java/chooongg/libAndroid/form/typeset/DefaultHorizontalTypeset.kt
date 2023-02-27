package chooongg.libAndroid.form.typeset

import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import chooongg.libAndroid.basic.ext.setTextAppearanceAttr
import chooongg.libAndroid.form.R
import chooongg.libAndroid.form.item.FormItem
import com.google.android.material.textview.MaterialTextView

class DefaultHorizontalTypeset : FormTypeset(Gravity.CENTER_VERTICAL or Gravity.START) {

    override fun onCreateItemTypesetLayout(parent: ViewGroup): ViewGroup {
        return LinearLayoutCompat(parent.context).apply {
            orientation = LinearLayoutCompat.HORIZONTAL
            MaterialTextView(context).also {
                it.id = R.id.tvFormName
                it.setTextAppearanceAttr(com.google.android.material.R.attr.textAppearanceBodyMedium)
                addView(it)
            }
        }
    }

    override fun onBindItemTypesetLayout(parent: ViewGroup, item: FormItem) {
    }
}