package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.games.CommonResponse
import io.reactivex.Flowable

class EuDbRepository : DbRepository {
    override fun getGames(): Flowable<CommonResponse>? = null
}
