package alex.orobynskyi.niprices.networking;

import alex.orobynskyi.niprices.BuildConfig
import alex.orobynskyi.niprices.domain.models.currency.CurrencyResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("api/v7/countries")
    fun getCurrencyCountries(@Query("apiKey") apiKey: String = BuildConfig.CURRENCY_API_KEY): Single<CurrencyResponse>

    @GET("api/v7/convert")
    fun getYourCurrencyRate(@Query("q") query: String, @Query("compat") compactResponse: String = "y",
                            @Query("apiKey") apiKey: String = BuildConfig.CURRENCY_API_KEY): Single<ResponseBody>
}
