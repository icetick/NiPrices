package alex.orobynskyi.niprices.domain.roomDb

import alex.orobynskyi.niprices.domain.models.games.GameDoc
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDocDao {
    @Query("SELECT * FROM gamedoc")
    fun getAll(): List<GameDoc>

    @Query("SELECT * FROM gamedoc WHERE uid IN (:gameDocIds)")
    fun loadAllByIds(gameDocIds: IntArray): List<GameDoc>

    @Query("SELECT * FROM gamedoc WHERE title LIKE :title")
    fun findByTitle(title: String): GameDoc

    @Insert
    fun insertAll(games: List<GameDoc>)

    @Delete
    fun delete(game: GameDoc)
}