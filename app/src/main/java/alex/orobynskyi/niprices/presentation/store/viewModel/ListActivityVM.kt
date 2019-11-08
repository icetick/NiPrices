package alex.orobynskyi.niprices.presentation.store.viewModel

import alex.orobynskyi.niprices.BR
import alex.orobynskyi.niprices.BuildConfig
import alex.orobynskyi.niprices.domain.models.currency.Rate
import alex.orobynskyi.niprices.domain.models.games.GameDoc
import alex.orobynskyi.niprices.domain.repository.Status
import alex.orobynskyi.niprices.networking.CurrencyManager
import alex.orobynskyi.niprices.networking.EshopInteractor
import alex.orobynskyi.niprices.presentation.base.ActionListener
import alex.orobynskyi.niprices.presentation.base.BaseViewModel
import alex.orobynskyi.niprices.presentation.base.DataBindingViewHolder
import alex.orobynskyi.niprices.utils.AppUtils
import androidx.appcompat.widget.SearchView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListActivityVM @Inject constructor(var eshopInteractor: EshopInteractor, var currencyManager: CurrencyManager): BaseViewModel(), ActionListener<GameDoc> {
    private var eupostsDisposable: Disposable? = null
    var euGames: MutableLiveData<List<GameDoc>> = MutableLiveData()
    var chosenGameUrl: MutableLiveData<String> = MutableLiveData()
    val taskSubscription: CompositeDisposable = CompositeDisposable()
    val currentRate: MutableLiveData<Rate> = MutableLiveData()

    val rateModifier = { dataBindingViewHolder: DataBindingViewHolder<ViewDataBinding, GameDoc> ->
        currentRate.observeForever {
            dataBindingViewHolder.getBinding().apply {
                setVariable(BR.currencyModifier, it.valX)
                notifyPropertyChanged(BR.currencyModifier)
            }
        }
    }

    private var searchSubject: PublishSubject<String>? = null
    var searchSubscription: DisposableObserver<List<GameDoc>>? = null

    val searchQueryTextListener: SearchView.OnQueryTextListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            searchGame(newText)
            return false
        }
    }

    override fun onCreated() {
        loadAllEuPosts()
        taskSubscription.add(currencyManager.loadCurrencies())
    }

    private fun subscribeRateChange() {
       /* currentRate.observeForever { rate ->
            val rateChangedValues = euGames.value?.onEach {game ->
               game.price_lowest_f = NativeWrapper.multiply(game.price_lowest_f!!, rate.valX)
            }
            euGames.postValue(rateChangedValues)
        }*/
    }

    private fun convertValue(): Double {
        return 0.toDouble()
    }

    fun updateCurrency(index: String) {
        val currencyRateDisposable = currencyManager.currencyApi.getYourCurrencyRate("GBP_"+index).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                currentRate.postValue(it.results.entries.iterator().next().value)
            }, {})

        taskSubscription.add(currencyRateDisposable)
    }

    override fun onClick(data: GameDoc) {
        chosenGameUrl.postValue(BuildConfig.ESHOP_ENDPOINT_UK+data.url)
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
            it.forEach { AppUtils.logE(it.toString()) }
        }
    }

    fun searchGame(keyword: String) {
        if(searchSubject==null) {
            searchSubject = PublishSubject.create()
            searchSubscription = searchSubject?.debounce(500, TimeUnit.MILLISECONDS)
                ?.distinctUntilChanged()
                ?.switchMap { searchValue ->
                    eshopInteractor.searchGamesByKeyword(searchValue)?.toObservable()
                }
                ?.subscribeWith(object : DisposableObserver<List<GameDoc>>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: List<GameDoc>) {
                        euGames.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        }

        searchSubject?.onNext(keyword)
    }

    override fun onCleared() {
        super.onCleared()
        taskSubscription.dispose()
        removeSearchSubscriptions()
    }

    fun removeSearchSubscriptions() {
        searchSubject = null
        searchSubscription?.dispose()
    }
}