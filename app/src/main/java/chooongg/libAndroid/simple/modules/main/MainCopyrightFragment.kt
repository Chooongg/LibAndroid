package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import chooongg.libAndroid.core.annotation.AppBarEnable
import com.chooongg.libAndroid.databinding.FragmentMainCopyrightBinding

@AppBarEnable
class MainCopyrightFragment : BaseMainFragment<FragmentMainCopyrightBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        title = "版权"
    }
}