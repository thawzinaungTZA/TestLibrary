package jp.co.hivelocity.hudidentity

import retrofit2.Retrofit

data class DataSourceConfig(
        val apiKey: String) {

    class Builder(
            var apiKey: String) {

        fun build() = DataSourceConfig(apiKey)
    }

    fun getRetrofit(): Retrofit {
        return RestAdapter.getRetrofit(this)
    }
}