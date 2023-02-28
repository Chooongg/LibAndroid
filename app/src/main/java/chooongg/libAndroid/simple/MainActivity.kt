package chooongg.libAndroid.simple

import android.os.Bundle
import androidx.lifecycle.ViewModel
import chooongg.libAndroid.core.activity.LibBindingModelActivity
import chooongg.libAndroid.core.annotation.AppBarNavigationEnable
import chooongg.libAndroid.core.widget.viewPager.FragmentAdapter
import chooongg.libAndroid.simple.modules.main.*
import com.chooongg.libAndroid.R
import com.chooongg.libAndroid.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

@AppBarNavigationEnable(false)
class MainActivity : LibBindingModelActivity<ActivityMainBinding, MainActivity.MainViewModel>() {

    class MainViewModel : ViewModel() {
        var navigationSelectedItemId = 0
    }

    private val navigationBarView get() = binding.navigationView as NavigationBarView

    private val fragmentAdapter by lazy {
        FragmentAdapter(
            activity,
            mutableListOf(
                MainSummaryFragment(),
                MainCoreFragment(),
                MainPluginFragment(),
                MainCopyrightFragment(),
                MainAboutFragment()
            )
        )
    }

    override fun initLayout() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        with(binding.viewPager) {
            isUserInputEnabled = false
            offscreenPageLimit = fragmentAdapter.fragments.size
            adapter = fragmentAdapter
        }
        with(navigationBarView) {
            inflateMenu(R.menu.main)
            if (model.navigationSelectedItemId != 0) {
                selectedItemId = model.navigationSelectedItemId
            } else {
                model.navigationSelectedItemId = selectedItemId
            }
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.summary -> {
                        binding.viewPager.setCurrentItem(0, false)
                    }
                    R.id.core -> {
                        binding.viewPager.setCurrentItem(1, false)
                    }
                    R.id.plugin -> {
                        binding.viewPager.setCurrentItem(2, false)
                    }
                    R.id.copyright -> {
                        binding.viewPager.setCurrentItem(3, false)
                    }
                    R.id.about -> {
                        binding.viewPager.setCurrentItem(4, false)
                    }
                    else -> return@setOnItemSelectedListener false
                }
                true
            }
        }
    }
}