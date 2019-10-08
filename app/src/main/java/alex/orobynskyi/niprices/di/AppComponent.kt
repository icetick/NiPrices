package alex.orobynskyi.niprices.di
import alex.orobynskyi.niprices.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ApiModule::class, DbModule::class, ViewModelModule::class])
interface AppComponent: AndroidInjector<App>