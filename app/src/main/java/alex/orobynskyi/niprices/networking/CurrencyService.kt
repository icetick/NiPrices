package alex.orobynskyi.niprices.networking;

import alex.orobynskyi.niprices.domain.models.currency.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("api/latest")
    fun getLatestCurrencyRates(@Query("base") baseCurrency: String? = null): Single<CurrencyResponse>
}
