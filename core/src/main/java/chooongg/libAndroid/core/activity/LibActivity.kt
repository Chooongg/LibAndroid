package chooongg.libAndroid.core.activity

import android.os.Bundle
import androidx.appcompat.R
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ViewStubCompat

class LibActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(findViewById<ViewStubCompat>(R.id.action_mode_bar_stub)) {
        }
    }
}