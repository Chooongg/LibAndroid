package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import chooongg.libAndroid.core.annotation.AppBarEnable
import com.chooongg.libAndroid.databinding.FragmentMainSummaryBinding

@AppBarEnable
class MainSummaryFragment : BaseMainFragment<FragmentMainSummaryBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        title = "概述"
    }

}