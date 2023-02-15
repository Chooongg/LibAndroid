package chooongg.libAndroid.core.widget.stateLayout

import chooongg.libAndroid.core.widget.stateLayout.state.AbstractState
import kotlin.reflect.KClass

interface OnStateChangeListener {
    fun onStateChange(state: KClass<out AbstractState>)
}