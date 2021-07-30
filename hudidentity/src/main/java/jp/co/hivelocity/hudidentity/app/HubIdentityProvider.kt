package jp.co.hivelocity.hudidentity.app

import android.app.Activity
import android.content.Intent
import android.util.Log
import jp.co.hivelocity.hudidentity.DataSourceConfig
import jp.co.hivelocity.hudidentity.model.IntentResult
import jp.co.hivelocity.hudidentity.app.ui.provider.ProviderActivity

object HubIdentityProvider {
    private const val DEFAULT_SCHEME = "redirect"
    private const val DEFAULT_DOMAIN = "jp.co.hivelocity.hubidentity"

    class Builder(
        var apiKey: String,
        var scheme: String = DEFAULT_SCHEME,
        var domain: String = DEFAULT_DOMAIN
    ) {
        fun scheme(scheme: String): Builder {
            this.scheme = scheme
            return this
        }

        fun domain(domain: String): Builder {
            this.domain = domain
            return this
        }

        fun start(activity: Activity, callback: LoginCallback) {
            resetManager()

            val manager = HubIdentityManager(apiKey, callback)
            manager.scheme = scheme
            manager.domain = domain
            hubIdentityManager = manager

            ProviderActivity.startHubIdentityMain(activity)
        }
    }

    fun login(apiKey: String): Builder {
        return Builder(apiKey)
    }

    private var hubIdentityManager: HubIdentityManager? = null

    fun dataSourceBuildConfig(): DataSourceConfig? {
        return hubIdentityManager?.dataSourceBuildConfig()
    }

    fun getApiKey(): String {
        return hubIdentityManager?.apiKey ?: ""
    }

    fun getScheme(): String {
        return hubIdentityManager?.scheme ?: DEFAULT_SCHEME
    }

    fun getDomain(): String {
        return hubIdentityManager?.domain ?: DEFAULT_DOMAIN
    }

    fun resume(intent: Intent): Boolean {
        if (hubIdentityManager == null) {
            Log.w(
                "HubIdentityProvider",
                "There is no previous instance of this provider."
            )
            return false
        }

        val result = IntentResult(intent)
        val success: Boolean =
            hubIdentityManager!!.resume(result)
        if (success) {
            resetManager()
        }
        return success
    }

    fun resetManager() {
        hubIdentityManager = null
    }
}