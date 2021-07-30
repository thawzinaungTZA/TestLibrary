package jp.co.hivelocity.hudidentity

import jp.co.hivelocity.hudidentity.source.ProviderRepository
import jp.co.hivelocity.hudidentity.source.remote.ProviderRemoteDataSource

object DataSourceInjection {

    fun configureProviderRepository(config: DataSourceConfig): ProviderRepository {
        return ProviderRepository.getInstance(ProviderRemoteDataSource.getInstance(config))
    }
}