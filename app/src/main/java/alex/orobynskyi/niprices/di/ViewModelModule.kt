package alex.orobynskyi.niprices.di
import alex.orobynskyi.niprices.presentation.store.viewModel.ListViewModel
import alex.orobynskyi.niprices.presentation.store.viewModel.MainViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey( MainViewModel::class )
    // Bind my View Model here
    abstract fun bindMainViewModel( mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    // Bind my View Model here
    abstract fun bindListViewModel( listViewModel: ListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}