package alex.orobynskyi.niprices.di

import alex.orobynskyi.niprices.App
import alex.orobynskyi.niprices.domain.repository.DbRepository
import alex.orobynskyi.niprices.domain.repository.EuDbRepository
import alex.orobynskyi.niprices.domain.roomDb.AppDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DbModule {
    @Provides
    @Named("dbservice")
    fun provideDbRepository(database: AppDatabase): DbRepository {
        return EuDbRepository(database)
    }

    @Provides
    fun provideApplicationDb(context: App): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "niprices-db").build()
    }
}
