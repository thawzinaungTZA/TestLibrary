package jp.co.hivelocity.hudidentity

import jp.co.hivelocity.hvcnetwork.auth.BaseApiAuth
import java.util.*

class HvcApiAuth(
    var apiKey: String,
    var headerNameForAPIKey: String
) : BaseApiAuth() {

    override fun getHeaders(): HashMap<String, String> {
        val dic = HashMap<String, String>()
        dic[headerNameForAPIKey] = apiKey
        return dic
    }
}