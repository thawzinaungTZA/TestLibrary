package jp.co.hivelocity.hudidentity.source.remote

import io.reactivex.Observable
import jp.co.hivelocity.hudidentity.DataSourceConfig
import jp.co.hivelocity.hudidentity.api.ProviderApi
import jp.co.hivelocity.hudidentity.model.provider.Provider
import jp.co.hivelocity.hudidentity.source.ProviderDataSource
import retrofit2.Retrofit

class ProviderRemoteDataSource(private val config: DataSourceConfig) : ProviderDataSource {

    companion object {
        fun getInstance(config: DataSourceConfig): ProviderRemoteDataSource {
            return ProviderRemoteDataSource(config)
        }
    }

    private fun provideProviderApi(): ProviderApi {
        val retrofit: Retrofit = config.getRetrofit()
        return retrofit.create(ProviderApi::class.java)

    }

    override fun getProviders(): Observable<List<Provider>> {
        return provideProviderApi().getProviders()
    }

}