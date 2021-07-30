package jp.co.hivelocity.hudidentity.api

import io.reactivex.Observable
import jp.co.hivelocity.hudidentity.model.provider.Provider
import retrofit2.http.GET

interface ProviderApi {
    @GET("providers")
    fun getProviders(): Observable<List<Provider>>
}