package chooongg.libAndroid.basic.ext

import android.widget.TextView
import chooongg.libAndroid.basic.ext.attrResourcesId

fun TextView.setTextAppearanceAttr(attrId: Int) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        setTextAppearance(attrResourcesId(attrId, 0))
    } else {
        @Suppress("DEPRECATION")
        setTextAppearance(context, attrResourcesId(attrId, 0))
    }
}