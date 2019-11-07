package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.currency.Rate
import alex.orobynskyi.niprices.domain.models.games.GameDoc
import alex.orobynskyi.niprices.domain.roomDb.AppDatabase
import io.reactivex.Flowable

class EuDbRepository(val database: AppDatabase) : DbRepository {
    override fun saveCurrencyRates(results: HashMap<String, Rate>) {
        database.currenciesDao().insertAll(results.values.toList())
    }

    override fun saveGames(games: List<GameDoc>) {
        games.isNotEmpty().let {
            database.gamedocsDao().insertAll(games)
        }
    }

    override fun getGames(): Flowable<List<GameDoc>>? = database.gamedocsDao().getAll()

    override fun searchGame(keyWord: String): Flowable<List<GameDoc>>? {
        return database.gamedocsDao().findByTitle(keyWord)
    }


}
