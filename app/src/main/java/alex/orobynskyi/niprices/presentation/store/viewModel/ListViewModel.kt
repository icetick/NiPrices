package alex.orobynskyi.niprices.presentation.store.viewModel

import alex.orobynskyi.niprices.domain.models.games.GameDoc
import alex.orobynskyi.niprices.domain.repository.Status
import alex.orobynskyi.niprices.networking.EshopInteractor
import alex.orobynskyi.niprices.presentation.base.ActionListener
import alex.orobynskyi.niprices.presentation.base.BaseViewModel
import alex.orobynskyi.niprices.utils.logE
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListViewModel @Inject constructor(var eshopInteractor: EshopInteractor): BaseViewModel(), ActionListener<GameDoc> {
    private var eupostsDisposable: Disposable? = null
    var euGames: MutableLiveData<List<GameDoc>> = MutableLiveData()

    override fun onCreated() {
        loadAllEuPosts()
    }

    override fun onClick(data: GameDoc) {

    }

    fun loadAllEuPosts() {
        eupostsDisposable = eshopInteractor.getEuGameData(false)?.subscribe({ resource ->
            when (resource.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    euGames.value = resource.data?.response?.docs
                }
                Status.ERROR -> {
                }
            }
        }, { throwable ->
        })

        euGames.observeForever {
            it.forEach { logE(it.toString()) }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}