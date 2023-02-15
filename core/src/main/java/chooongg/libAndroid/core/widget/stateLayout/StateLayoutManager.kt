package chooongg.libAndroid.core.widget.stateLayout

import chooongg.libAndroid.core.widget.stateLayout.animation.FadeStateLayoutAnimation
import chooongg.libAndroid.core.widget.stateLayout.animation.StateLayoutAnimation
import chooongg.libAndroid.core.widget.stateLayout.state.AbstractState
import chooongg.libAndroid.core.widget.stateLayout.state.SuccessState
import kotlin.reflect.KClass

object StateLayoutManager {
    var defaultState: KClass<out AbstractState> = SuccessState::class
    var enableAnimation = true
    var animation: StateLayoutAnimation = FadeStateLayoutAnimation
}