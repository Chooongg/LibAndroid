package chooongg.libAndroid.form.cteator

import chooongg.libAndroid.form.item.FormItem

class FormGroupCreator {

    internal val groupList = mutableListOf<FormItem>()

    fun add(item: FormItem) {
        groupList.add(item)
    }
}