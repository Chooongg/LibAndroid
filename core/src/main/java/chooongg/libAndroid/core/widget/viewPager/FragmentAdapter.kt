package chooongg.libAndroid.core.widget.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import chooongg.libAndroid.core.fragment.LibFragment

class FragmentAdapter<T : LibFragment> : FragmentStateAdapter {

    val fragments: MutableList<T>

    constructor(fragmentActivity: FragmentActivity, fragments: MutableList<T>) : super(
        fragmentActivity
    ) {
        this.fragments = fragments
    }

    constructor(fragment: Fragment, fragments: MutableList<T>) : super(fragment) {
        this.fragments = fragments
    }

    constructor(
        fragmentManager: FragmentManager, lifecycle: Lifecycle, fragments: MutableList<T>
    ) : super(fragmentManager, lifecycle) {
        this.fragments = fragments
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun getTitle(position: Int) = fragments[position].title

    fun getLiftOnScrollTargetId(position: Int) = fragments[position].getLiftOnScrollTargetId()
}