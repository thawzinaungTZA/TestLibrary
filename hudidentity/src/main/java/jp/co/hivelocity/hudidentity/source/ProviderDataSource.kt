package jp.co.hivelocity.hudidentity.source

import io.reactivex.Observable
import jp.co.hivelocity.hudidentity.model.provider.Provider

interface ProviderDataSource {
    fun getProviders(): Observable<List<Provider>>
}