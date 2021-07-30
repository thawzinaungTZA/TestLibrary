package jp.co.hivelocity.hudidentity

import io.reactivex.observers.TestObserver
import jp.co.hivelocity.hudidentity.model.provider.Provider
import jp.co.hivelocity.hudidentity.source.ProviderDataSource
import jp.co.hivelocity.hudidentity.source.ProviderRepository
import jp.co.hivelocity.hudidentity.source.remote.ProviderRemoteDataSource
import org.junit.Before
import org.junit.Test

class ProviderRepositoryTest {
    private lateinit var providerRepository: ProviderDataSource

    @Before
    fun setupBioRepository() {
        providerRepository = ProviderRepository.getInstance(ProviderRemoteDataSource.getInstance(buildConfig()))
    }

    private fun buildConfig(): DataSourceConfig {
        return DataSourceConfig
                .Builder("pub_5ueGLgnuHeY4EjwnEY0Rq2Ul1AFa/vfrbgsFoG+zHGM=")
                .build()
    }

    @Test
    fun test_getBios() {
        val providerTestObserver: TestObserver<List<Provider>> = TestObserver()
        providerRepository.getProviders()
                .subscribe(providerTestObserver)
        providerTestObserver.awaitTerminalEvent()
        providerTestObserver.assertNoErrors()
        providerTestObserver.assertComplete()
    }

}