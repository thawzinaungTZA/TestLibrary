package jp.co.hivelocity.hudidentity

import jp.co.hivelocity.hudidentity.config.ApiConfig
import jp.co.hivelocity.hvcnetwork.factory.RxErrorHandlingCallAdapterFactory
import jp.co.hivelocity.hvcnetwork.provider.HttpClientProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestAdapter {

    private fun provideHeaders(config: DataSourceConfig): HvcApiAuth {
        return HvcApiAuth(
                config.apiKey,
                ApiConfig.HeaderNameForApiKey
        )
    }

    fun getRetrofit(config: DataSourceConfig): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(HttpClientProvider.provideOkHttpClient(provideHeaders(config)))
                .build()
    }
}