package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import chooongg.libAndroid.core.fragment.LibBindingFragment
import com.chooongg.libAndroid.databinding.FragmentMainSummaryBinding

class MainSummaryFragment : BaseMainFragment<FragmentMainSummaryBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        binding.topAppBarLayout.appBarLayout.addLiftOnScrollListener { _, backgroundColor ->
            activity?.window?.statusBarColor = backgroundColor
        }
    }
}