package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.games.GameDoc
import io.reactivex.Flowable

interface DbRepository {
    fun getGames(): Flowable<List<GameDoc>>?
    fun saveGames(games: List<GameDoc>)
    fun searchGame(keyWord: String): Flowable<List<GameDoc>>?
}
