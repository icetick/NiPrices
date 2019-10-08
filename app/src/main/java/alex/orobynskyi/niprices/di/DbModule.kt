package alex.orobynskyi.niprices.di

import alex.orobynskyi.niprices.domain.repository.DbRepository
import alex.orobynskyi.niprices.domain.repository.EuDbRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DbModule {
    @Provides
    @Named("dbservice")
    fun provideDbRepository(): DbRepository {
        return EuDbRepository()
    }
}
