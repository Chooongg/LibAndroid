package chooongg.libAndroid.simple.modules.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import chooongg.libAndroid.core.annotation.Title
import chooongg.libAndroid.core.fragment.LibBindingModelFragment
import chooongg.libAndroid.form.FormAdapter
import chooongg.libAndroid.form.item.FormText
import chooongg.libAndroid.form.style.DefaultStyle
import com.chooongg.libAndroid.databinding.FragmentMainPluginBinding

@Title("插件")
class MainPluginFragment :
    LibBindingModelFragment<FragmentMainPluginBinding, MainPluginFragment.PluginViewModel>() {

    class PluginViewModel : ViewModel() {
        val formAdapter = FormAdapter(2, true)
    }

    override fun initView(savedInstanceState: Bundle?) {
        model.formAdapter.bind(binding.recyclerView)
        model.formAdapter.addPart(DefaultStyle()) {
            createGroup {
                add(FormText("测试1", "null", "sdfasdfasdf"))
            }
        }
    }
}