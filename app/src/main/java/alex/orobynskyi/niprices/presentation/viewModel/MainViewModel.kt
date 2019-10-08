package alex.orobynskyi.niprices.presentation.viewModel

import alex.orobynskyi.niprices.domain.models.games.Doc
import alex.orobynskyi.niprices.domain.repository.Status
import alex.orobynskyi.niprices.networking.EshopInteractor
import alex.orobynskyi.niprices.presentation.base.BaseViewModel
import alex.orobynskyi.niprices.utils.logE
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(var eshopInteractor: EshopInteractor) : BaseViewModel() {
    private var eupostsDisposable: Disposable? = null
    var euGames: MutableLiveData<List<Doc>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onCreated() {

    }

    fun loadAllEuPosts() {
        eupostsDisposable = eshopInteractor.getEuGameData()?.subscribe({ resource ->
            when (resource.status) {
                Status.LOADING -> {
                    loading.value = true
                }
                Status.SUCCESS -> {
                    euGames.value = resource.data?.response?.docs
                    loading.postValue(false)
                }
                Status.ERROR -> {
                    loading.value = false
                }
            }
        }, { throwable ->
            loading.value = false
        })

        euGames.observeForever {
            it.forEach { logE(it.toString()) }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}