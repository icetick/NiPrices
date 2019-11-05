package alex.orobynskyi.niprices.networking

import alex.orobynskyi.niprices.domain.models.currency.Currency
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class CurrencyManager @Inject constructor(@Named("currencyservice") var currencyApi: CurrencyService) {
    var currencies: MutableLiveData<HashMap<String, Currency>> = MutableLiveData()

    fun loadCurrencies(): Disposable {
        return currencyApi.getCurrencyCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                this.currencies.value = it.results
                loadRatesForCurrency("GBP")
            }, {})
    }

    fun loadRatesForCurrency(originalCurrency: String): Disposable {
        //Free API supports only 10 currencies per one request, so we map it
        //into different requests
        var query = createRatesQuery(originalCurrency).split(",")
        val queryGroup =
            query.withIndex().groupBy { it.index / 10 }.map { it.value.map { it.value } }

        return Observable.fromIterable(queryGroup).map {
            currencyApi.getYourCurrencyRate(it.joinToString(","))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Log.e("TAG", it.results.toString())
                }, {})
        }.subscribe()
    }

    fun createRatesQuery(originalCurrency: String): String {
        return currencies.value?.values?.distinctBy { it.currencyId }?.joinToString(",") {
            originalCurrency + "_" + it.currencyId
        }!!
    }

    fun getCurrencyCode(price: String): String? {
        return currencies.value?.filter { it.value.currencySymbol.equals(price.split(" ")[1]) }
            ?.entries?.first()?.value?.currencyId
    }
}