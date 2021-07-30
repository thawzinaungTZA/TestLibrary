package jp.co.hivelocity.hudidentity.app

import jp.co.hivelocity.hudidentity.model.Credentials

interface LoginCallback {
    fun onFailure(exception: AuthenticationException)

    fun onSuccess(credentials: Credentials)
}