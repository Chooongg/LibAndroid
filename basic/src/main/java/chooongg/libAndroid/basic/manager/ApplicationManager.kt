package chooongg.libAndroid.basic.manager

import android.app.Application

internal object ApplicationManager {
    internal var application: Application? = null
        private set

    internal fun initialize(application: Application) {
        this.application = application.apply {
            registerActivityLifecycleCallbacks(ActivityLifecycleManager)
        }
    }
}