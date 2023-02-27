package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import chooongg.libAndroid.core.annotation.AppBarEnable
import chooongg.libAndroid.core.fragment.LibBindingModelFragment
import chooongg.libAndroid.form.FormAdapter
import chooongg.libAndroid.form.item.FormText
import chooongg.libAndroid.form.style.DefaultStyle
import com.chooongg.libAndroid.databinding.FragmentMainPluginBinding

@AppBarEnable
class MainPluginFragment :
    LibBindingModelFragment<FragmentMainPluginBinding, MainPluginFragment.PluginViewModel>() {

    class PluginViewModel : ViewModel() {
        val formAdapter = FormAdapter(2, true)
    }

    override fun initView(savedInstanceState: Bundle?) {
        title = "插件"
        model.formAdapter.bind(binding.recyclerView)
        model.formAdapter.addPart(DefaultStyle()) {
            createGroup {
                add(FormText("测试1", "null", "sdfasdfasdf"))
            }
        }
    }
}