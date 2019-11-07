package alex.orobynskyi.niprices.domain.roomDb

import alex.orobynskyi.niprices.domain.models.currency.Rate
import androidx.room.*
import io.reactivex.Flowable

@Dao
interface RateDao {
    @Query("SELECT * FROM rate")
    fun getAll(): Flowable<List<Rate>>

    @Query("SELECT * FROM rate WHERE id IN (:rateIds)")
    fun loadAllByIds(rateIds: IntArray): Flowable<List<Rate>>

    @Query("SELECT * FROM rate WHERE `to` LIKE '%' || :name || '%' OR fr LIKE '%' || :name || '%' ")
    fun findByName(name: String): Flowable<List<Rate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Rate>)

    @Delete
    fun delete(item: Rate)
}