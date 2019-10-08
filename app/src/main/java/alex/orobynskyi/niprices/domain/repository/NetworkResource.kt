package alex.orobynskyi.niprices.domain.repository

import androidx.annotation.MainThread
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


abstract class NetworkResource<R> @MainThread
constructor() {
    private val result: Flowable<Resource<R>>?

    init {
        val source: Flowable<Resource<R>>?
        if (shouldFetch()) {
            source = createCall()
                .doOnSubscribe { Resource.loading(it) }
                .doOnNext { this.saveNetworkCallResult(it) }
                .flatMap { apiResponse ->
                    loadFromDb()?.toObservable()
                        ?.map<Resource<R>> {
                            Resource.success(apiResponse)
                        } ?: Observable.just(Resource.success(apiResponse))
                }
                .doOnError { this.onFetchFailed(it) }
                .onErrorResumeNext { t: Throwable ->
                    return@onErrorResumeNext loadFromDb()
                        ?.toObservable()
                        ?.map { data ->
                            val apiResource: Resource<R>

                            if (t is HttpException && t.code() >= 400 && t.code() < 500) {
                                apiResource = Resource.error(t.message)
                            } else {
                                apiResource = Resource.error(t.message)
                            }

                            apiResource
                        }
                }
                .toFlowable(BackpressureStrategy.LATEST)
        } else {
            source = loadFromDb()
                ?.subscribeOn(Schedulers.io())
                ?.map { Resource.success(it) }
        }

        result = Flowable.concat(
            initLoadDb()
                ?.map<Resource<R>> { Resource.loading(it) }
                ?.take(1)?:Flowable.empty(), source
        ).subscribeOn(Schedulers.io())
    }

    fun asObservable(): Observable<Resource<R>>? {
        return result?.toObservable()
    }

    fun asMainThreadObservable(): Observable<Resource<R>>? {
        return result?.toObservable()?.observeOn(AndroidSchedulers.mainThread())
    }

    fun asFlowable(): Flowable<Resource<R>>? {
        return result
    }

    protected fun onFetchFailed(t: Throwable) {}

    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<R>?

    @MainThread
    protected abstract fun createCall(): Observable<R>

    protected abstract fun saveNetworkCallResult(item: R)

    @MainThread
    protected fun initLoadDb(): Flowable<R>? {
        return loadFromDb()
    }

}