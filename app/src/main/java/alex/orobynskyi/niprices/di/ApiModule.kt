package alex.orobynskyi.niprices.di

import alex.orobynskyi.niprices.BuildConfig
import alex.orobynskyi.niprices.domain.repository.ApiRepository
import alex.orobynskyi.niprices.domain.repository.DbRepository
import alex.orobynskyi.niprices.domain.repository.EuApiRepository
import alex.orobynskyi.niprices.networking.EshopInteractor
import alex.orobynskyi.niprices.networking.EshopService
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class ApiModule {
    @Provides
    internal fun provideServerCommunicator(apiRepository: ApiRepository, repository: DbRepository): EshopInteractor {
        return EshopInteractor(apiRepository, repository)
    }

    @Provides
    internal fun provideEuApiRepository(@Named("apiservice") apiService: EshopService): ApiRepository {
        return EuApiRepository(apiService)
    }

    @Provides
    @Named("apiservice")
    internal fun provideApiService(@Named("api") retrofit: Retrofit): EshopService {
        return retrofit.create(EshopService::class.java)
    }

    @Provides
    @Named("api")
    internal fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(BuildConfig.EUROPE_ENDPOINT).build()
    }

    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder {
        val builder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        //builder.addNetworkInterceptor(new HttpLoggingInterceptor());

        /*    if (BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY );
            builder.addInterceptor(loggingInterceptor);
        }*/

        return Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    }

}
