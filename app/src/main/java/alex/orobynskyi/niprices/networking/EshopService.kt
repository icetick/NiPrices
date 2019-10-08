package alex.orobynskyi.niprices.networking

import alex.orobynskyi.niprices.domain.models.games.CommonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EshopService {
    //f1=product_code_txt,title,date_from,nsuid_txt,image_url_sq_s&fq=type:GAME AND system_type:nintendoswitch* AND product_code_txt:*&q=*&rows=9999&sort=sorting_title asc&start=0&wt=json
    @GET("select")
    fun getEuGames(@Query("f1") filtersWithComma: String = "product_code_txt,title,date_from,nsuid_txt,image_url_sq_s",
                   @Query("fq") filterQuery: String = "type:GAME AND system_type:nintendoswitch* AND product_code_txt:*",
                   @Query("q") query: String = "*",
                   @Query("rows") rows: Int = 9999,
                   @Query("sort") sort: String = "sorting_title asc",
                   @Query("start") start: Int = 0,
                   @Query("wt") returnType: String = "json"): Single<CommonResponse>
}