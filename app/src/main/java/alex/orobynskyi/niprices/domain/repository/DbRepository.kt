package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.domain.models.games.GameDoc

interface DbRepository {
    fun getGames(): List<GameDoc>?
    fun saveGames(games: List<GameDoc>)
}
