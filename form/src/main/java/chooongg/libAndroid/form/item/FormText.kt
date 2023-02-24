package chooongg.libAndroid.form.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import chooongg.libAndroid.form.FormViewHolder
import chooongg.libAndroid.form.R

class FormText(name: String, field: String?, override var content: CharSequence?) :
    FormItem(name, field) {

    override fun onCreateItemView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.form_item_text, parent, false)
    }

    override fun onBindItemView(holder: FormViewHolder, payloads: MutableList<Any>?) {
        holder.itemView.findViewById<TextView>(R.id.formContent)
    }
}