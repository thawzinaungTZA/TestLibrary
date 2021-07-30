package jp.co.hivelocity.hudidentity.app

import jp.co.hivelocity.hudidentity.DataSourceConfig
import jp.co.hivelocity.hudidentity.model.Credentials
import jp.co.hivelocity.hudidentity.model.IntentResult

class HubIdentityManager(var apiKey: String) {

    private var callback: LoginCallback? = null
    var scheme: String? = null
    var domain: String? = null

    constructor(apiKey: String, callback: LoginCallback) : this(apiKey) {
        this.apiKey = apiKey
        this.callback = callback
    }

    fun dataSourceBuildConfig(): DataSourceConfig {
        return DataSourceConfig
            .Builder(apiKey)
            .build()
    }

    fun resume(result: IntentResult): Boolean {
        if (!result.isValid()) {
            return false
        }

        if (result.isCanceled()) {
            callback?.onFailure(
                AuthenticationException(
                    AuthenticationException.ERROR_CODE_AUTHENTICATION_CANCELED,
                    AuthenticationException.ERROR_MESSAGE_AUTHENTICATION_CANCELED
                )
            )
            return false
        }

        if (result.getAccessToken() == null) {
            return false
        }

        callback?.onSuccess(Credentials(result.getAccessToken()))
        return true
    }

}