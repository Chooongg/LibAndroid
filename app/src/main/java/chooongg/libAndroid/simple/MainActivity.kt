package chooongg.libAndroid.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chooongg.libAndroid.core.activity.LibActivity
import com.chooongg.libAndroid.databinding.ActivityMainBinding
import com.chooongg.libAndroid.databinding.ItemMainBinding

class MainActivity : LibActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter = Adapter(
        mutableListOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        binding.appBarLayout.addLiftOnScrollListener { elevation, backgroundColor ->
            window.statusBarColor = backgroundColor
        }
        binding.recyclerView.adapter = adapter
    }

    private class Adapter(val data: MutableList<String>) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {
        private class ViewHolder(val binding: ItemMainBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun getItemCount() = data.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.tvName.text = data[position]
        }
    }
}