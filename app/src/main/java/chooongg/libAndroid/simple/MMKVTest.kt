package chooongg.libAndroid.simple

import android.os.Parcel
import android.os.Parcelable
import chooongg.libAndroid.basic.mmkv.MMKVContainer

object MMKVTest : MMKVContainer() {

    var boolean by mmkvBoolean(false)
    var booleanOrNull by mmkvBooleanOrNull(null)
    var int by mmkvInt(1)
    var intOrNull by mmkvIntOrNull(null)
    var long by mmkvLong(1L)
    var longOrNull by mmkvLongOrNull(null)
    var float by mmkvFloat(1f)
    var floatOrNull by mmkvFloatOrNull(null)
    var double by mmkvDouble(1.0)
    var doubleOrNull by mmkvDoubleOrNull(null)
    var string by mmkvString("string")
    var stringOrNull by mmkvStringOrNull(null)
    var stringSet by mmkvStringSet(setOf("setItem"))
    var stringSetOrNull by mmkvStringSetOrNull(null)
    var bytes by mmkvBytes(byteArrayOf(1))
    var bytesOrNull by mmkvBytesOrNull(null)
    var parcelable by mmkvParcelable(TestParcelable())
    var parcelableOrNull by mmkvParcelableOrNull(null)

    class TestParcelable() : Parcelable {
        constructor(parcel: Parcel) : this() {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<TestParcelable> {
            override fun createFromParcel(parcel: Parcel): TestParcelable {
                return TestParcelable(parcel)
            }

            override fun newArray(size: Int): Array<TestParcelable?> {
                return arrayOfNulls(size)
            }
        }
    }
}