package alex.orobynskyi.niprices.di;

import alex.orobynskyi.niprices.presentation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity
}