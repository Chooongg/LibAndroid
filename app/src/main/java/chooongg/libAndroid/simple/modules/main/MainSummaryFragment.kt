package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import chooongg.libAndroid.core.annotation.AppBarEnable
import chooongg.libAndroid.core.appBar.TopLargeAppBarDelegate
import com.chooongg.libAndroid.R
import com.chooongg.libAndroid.databinding.FragmentMainSummaryBinding

@AppBarEnable
class MainSummaryFragment : BaseMainFragment<FragmentMainSummaryBinding>() {

    override fun getAppBarDelegate() = TopLargeAppBarDelegate()

    override fun initView(savedInstanceState: Bundle?) {
        setTitle(R.string.app_name)
    }
}