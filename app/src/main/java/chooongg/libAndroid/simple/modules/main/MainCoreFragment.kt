package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import chooongg.libAndroid.core.annotation.AppBarEnable
import com.chooongg.libAndroid.databinding.FragmentMainCoreBinding

@AppBarEnable
class MainCoreFragment : BaseMainFragment<FragmentMainCoreBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        title = "核心"
    }
}