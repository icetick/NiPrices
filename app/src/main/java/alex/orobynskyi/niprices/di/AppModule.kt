package alex.orobynskyi.niprices.di;

import alex.orobynskyi.niprices.presentation.store.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity
}