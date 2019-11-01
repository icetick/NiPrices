package alex.orobynskyi.niprices.presentation.store.viewModel

import alex.orobynskyi.niprices.BuildConfig
import alex.orobynskyi.niprices.domain.models.currency.Currency
import alex.orobynskyi.niprices.domain.models.currency.Rate
import alex.orobynskyi.niprices.domain.models.games.GameDoc
import alex.orobynskyi.niprices.domain.repository.Status
import alex.orobynskyi.niprices.networking.CurrencyService
import alex.orobynskyi.niprices.networking.EshopInteractor
import alex.orobynskyi.niprices.presentation.base.ActionListener
import alex.orobynskyi.niprices.presentation.base.BaseViewModel
import alex.orobynskyi.niprices.utils.AppUtils
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class ListViewModel @Inject constructor(var eshopInteractor: EshopInteractor, @Named("currencyservice")var currencyApi: CurrencyService): BaseViewModel(), ActionListener<GameDoc> {
    private var eupostsDisposable: Disposable? = null
    var euGames: MutableLiveData<List<GameDoc>> = MutableLiveData()
    var chosenGameUrl: MutableLiveData<String> = MutableLiveData()
    var currencies: MutableLiveData<HashMap<String, Currency>> = MutableLiveData()
    val taskSubscription: CompositeDisposable = CompositeDisposable()
    val currentRate: MutableLiveData<Rate> = MutableLiveData()

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
        loadCurrencies()
        subscribeRateChange()
    }

    private fun subscribeRateChange() {
        currentRate.observeForever {
            val rateChangedValues = euGames.value?.onEach {
            }
            euGames.postValue(rateChangedValues)
        }
    }

    private fun convertValue(): Double {

    }

    private fun loadCurrencies() {
        val currenciesDisposable = currencyApi.getCurrencyCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                this.currencies.postValue(it.results)
            }, {})
        
        taskSubscription.add(currenciesDisposable)
    }

    fun updateCurrency(index: String) {
        val currencyRateDisposable = currencyApi.getYourCurrencyRate("GBP_"+index).subscribeOn(Schedulers.io())
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