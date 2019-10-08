package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.games.CommonResponse
import io.reactivex.Flowable

interface DbRepository {
    fun getGames(): Flowable<CommonResponse>?
}
