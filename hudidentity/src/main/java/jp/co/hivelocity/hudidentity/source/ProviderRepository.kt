package jp.co.hivelocity.hudidentity.source

import io.reactivex.Observable
import jp.co.hivelocity.hudidentity.model.provider.Provider

class ProviderRepository(private val providerRemoteDataSource: ProviderDataSource) :
    ProviderDataSource {

    companion object {
        fun getInstance(providerRemoteDataSource: ProviderDataSource): ProviderRepository {
            return ProviderRepository(providerRemoteDataSource)
        }
    }

    override fun getProviders(): Observable<List<Provider>> {
        return providerRemoteDataSource.getProviders()
    }
}