package alex.orobynskyi.niprices.di
import alex.orobynskyi.niprices.presentation.viewModel.MainViewModel
import dagger.Component

@Component( modules = [ViewModelModule::class])
interface ViewModelComponent {
    // inject new view models
    fun inject(mainViewModel: MainViewModel)

}