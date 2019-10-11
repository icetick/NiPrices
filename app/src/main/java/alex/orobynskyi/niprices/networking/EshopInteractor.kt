package alex.orobynskyi.niprices.networking

import alex.orobynskyi.niprices.domain.models.games.CommonResponse
import alex.orobynskyi.niprices.domain.models.games.Response
import alex.orobynskyi.niprices.domain.models.games.ResponseHeader
import alex.orobynskyi.niprices.domain.repository.ApiRepository
import alex.orobynskyi.niprices.domain.repository.DbRepository
import alex.orobynskyi.niprices.domain.repository.NetworkResource
import alex.orobynskyi.niprices.domain.repository.Resource
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EshopInteractor @Inject constructor(var apiRepository: ApiRepository, var dbRepository: DbRepository) {
    fun getEuGameData(shouldFetch: Boolean): Observable<Resource<CommonResponse>>? {
        return object: NetworkResource<CommonResponse>() {
            override fun saveNetworkCallResult(item: CommonResponse) {
                dbRepository.saveGames(item.response.docs)
            }
            override fun loadFromDb(): Flowable<CommonResponse>? {
                val games = dbRepository.getGames()
                return if(games!=null) {
                    return games.map {
                       CommonResponse(Response(it, it.size, 0), ResponseHeader.empty())
                    }
                } else {
                    null
                }
            }
            override fun createCall(): Observable<CommonResponse> = apiRepository.getGames().toObservable()
            override fun shouldFetch(): Boolean = shouldFetch

        }.asMainThreadObservable()
    }

    fun getSingleEuGameData() = apiRepository.getGames().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}