package alex.orobynskyi.niprices.di

import alex.orobynskyi.niprices.App
import alex.orobynskyi.niprices.domain.repository.DbRepository
import alex.orobynskyi.niprices.domain.repository.EuDbRepository
import alex.orobynskyi.niprices.domain.roomDb.AppDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
class DbModule {
    @Provides
    fun provideDbRepository(database: AppDatabase): DbRepository {
        return EuDbRepository(database)
    }

    @Provides
    fun provideApplicationDb(): AppDatabase {
        return Room.databaseBuilder(App.getInstance(), AppDatabase::class.java, "niprices-db").build()
    }
}
