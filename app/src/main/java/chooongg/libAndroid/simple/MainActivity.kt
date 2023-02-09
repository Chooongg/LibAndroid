package chooongg.libAndroid.simple

import android.os.Bundle
import chooongg.libAndroid.core.activity.LibBindingActivity
import chooongg.libAndroid.core.viewBinding.inflateBinding
import com.chooongg.libAndroid.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : LibBindingActivity<ActivityMainBinding>({ inflateBinding(it) }) {


    override fun initView(savedInstanceState: Bundle?) {
        ActivityMainBinding.inflate(layoutInflater).navigationView as NavigationBarView
    }
}