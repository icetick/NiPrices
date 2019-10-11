package alex.orobynskyi.niprices.di
import alex.orobynskyi.niprices.presentation.store.viewModel.ListViewModel
import alex.orobynskyi.niprices.presentation.store.viewModel.MainViewModel
import dagger.Component

@Component( modules = [ViewModelModule::class])
interface ViewModelComponent {
    // inject new view models
    fun inject(mainViewModel: MainViewModel)
    fun inject(listViewModel: ListViewModel)
}