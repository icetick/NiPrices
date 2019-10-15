package alex.orobynskyi.niprices.presentation.store.viewModel

import alex.orobynskyi.niprices.domain.models.games.GameDoc
import alex.orobynskyi.niprices.domain.repository.Status
import alex.orobynskyi.niprices.networking.EshopInteractor
import alex.orobynskyi.niprices.presentation.base.BaseViewModel
import alex.orobynskyi.niprices.utils.AppUtils
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(var eshopInteractor: EshopInteractor) : BaseViewModel() {
    private var eupostsDisposable: Disposable? = null
    var euGames: MutableLiveData<List<GameDoc>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val proceed: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onCreated() {

    }

    fun loadAllEuPosts() {
        eupostsDisposable = eshopInteractor.getEuGameData(true)?.subscribe({ resource ->
            when (resource.status) {
                Status.LOADING -> {
                    loading.value = true
                }
                Status.SUCCESS -> {
                    loading.value = false
                    proceed.value = true
                }
                Status.ERROR -> {
                    loading.value = false
                }
            }
        }, { throwable ->
            loading.value = false
        })

        euGames.observeForever {
            it.forEach { AppUtils.logE(it.toString()) }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}