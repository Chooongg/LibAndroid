package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import chooongg.libAndroid.core.annotation.AppBarEnable
import com.chooongg.libAndroid.databinding.FragmentMainAboutBinding

@AppBarEnable
class MainAboutFragment : BaseMainFragment<FragmentMainAboutBinding>() {
    override fun initContent(savedInstanceState: Bundle?) {
        title = "关于"
    }
}