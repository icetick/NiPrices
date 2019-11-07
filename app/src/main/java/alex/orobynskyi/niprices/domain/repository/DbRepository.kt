package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.currency.Rate
import alex.orobynskyi.niprices.domain.models.games.GameDoc
import io.reactivex.Flowable

interface DbRepository {
    fun getGames(): Flowable<List<GameDoc>>?
    fun saveGames(games: List<GameDoc>)
    fun searchGame(keyWord: String): Flowable<List<GameDoc>>?
    fun saveCurrencyRates(results: HashMap<String, Rate>)
}
