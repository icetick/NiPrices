package alex.orobynskyi.niprices

import alex.orobynskyi.niprices.di.DaggerAppComponent
import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector

    companion object {
        private lateinit var instance: App
        @Synchronized fun getInstance(): App = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaggerAppComponent.create().inject(this)
    }
}