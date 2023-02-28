package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStateAtLeast
import chooongg.libAndroid.basic.ext.showToast
import chooongg.libAndroid.core.annotation.AppBarEnable
import com.chooongg.libAndroid.databinding.FragmentMainAboutBinding
import kotlinx.coroutines.launch

@AppBarEnable
class MainAboutFragment : BaseMainFragment<FragmentMainAboutBinding>() {
    override fun initContent(savedInstanceState: Bundle?) {
        title = "关于"
    }
}