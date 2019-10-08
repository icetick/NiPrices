package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.games.CommonResponse
import io.reactivex.Single

interface ApiRepository {
    fun getGames(): Single<CommonResponse>
}
