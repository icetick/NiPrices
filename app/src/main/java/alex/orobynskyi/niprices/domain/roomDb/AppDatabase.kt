package alex.orobynskyi.niprices.domain.roomDb

import alex.orobynskyi.niprices.domain.models.currency.Rate
import alex.orobynskyi.niprices.domain.models.games.GameDoc
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(GameDoc::class, Rate::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gamedocsDao(): GameDocDao
    abstract fun currenciesDao(): RateDao
}
