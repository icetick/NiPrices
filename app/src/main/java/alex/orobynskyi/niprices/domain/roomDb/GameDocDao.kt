package alex.orobynskyi.niprices.domain.roomDb

import alex.orobynskyi.niprices.domain.models.games.GameDoc
import androidx.room.*
import io.reactivex.Flowable

@Dao
interface GameDocDao {
    @Query("SELECT * FROM gamedoc")
    fun getAll(): Flowable<List<GameDoc>>

    @Query("SELECT * FROM gamedoc WHERE fs_id IN (:gameDocIds)")
    fun loadAllByIds(gameDocIds: IntArray): Flowable<List<GameDoc>>

    @Query("SELECT * FROM gamedoc WHERE title LIKE :title")
    fun findByTitle(title: String): Flowable<GameDoc>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(games: List<GameDoc>)

    @Delete
    fun delete(game: GameDoc)
}