package jp.co.hivelocity.hudidentity.model

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log

data class IntentResult(
    var requestCode: Int? = null,
    var resultCode: Int? = null,
    var intent: Intent? = null
) {

    constructor(intent: Intent?) : this(
        DEFAULT_REQUEST_CODE,
        if (intent?.data == null) Activity.RESULT_OK else Activity.RESULT_CANCELED,
        intent
    ) {
        this.requestCode = DEFAULT_REQUEST_CODE
        this.resultCode =
            if (getIntentData() != null) Activity.RESULT_OK else Activity.RESULT_CANCELED
        this.intent = intent
    }

    companion object {
        private const val TAG: String = "IntentResult"
        private const val DEFAULT_REQUEST_CODE: Int = -100
        private const val ACCESS_TOKEN: String = "access_token"
        private const val USER_TOKEN: String = "user_token"
    }

    fun isValid(expectedRequestCode: Int? = null): Boolean {
        val validRequestCode =
            requestCode == DEFAULT_REQUEST_CODE || requestCode == expectedRequestCode
        val validResultCode = isCanceled() || resultCode == Activity.RESULT_OK
        if (validRequestCode && validResultCode) {
            return true
        }
        Log.d(
            TAG,
            "Result is invalid: Either the received Intent is null or the Request Code doesn't match the expected one."
        )
        return false
    }

    fun isCanceled(): Boolean {
        return resultCode == Activity.RESULT_CANCELED && intent != null && getIntentData() == null
    }

    fun getIntentData(): Uri? {
        return intent?.data
    }

    fun getAccessToken(): String? {
        return getIntentData()?.getQueryParameter(ACCESS_TOKEN)
    }

    fun getUserToken(): String? {
        return getIntentData()?.getQueryParameter(USER_TOKEN)
    }
}