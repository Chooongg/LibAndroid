package chooongg.libAndroid.recyclerView.viewHolder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding as ViewBinding1

class BindingViewHolder<B : ViewBinding1>(val binding: B) : RecyclerView.ViewHolder(binding.root){
    val context get() = itemView.context
}