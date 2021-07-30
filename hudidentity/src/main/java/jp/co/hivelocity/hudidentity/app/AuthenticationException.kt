package jp.co.hivelocity.hudidentity.app

data class AuthenticationException(
    val code: String,
    val message: String
) {
    companion object {
        const val ERROR_CODE_AUTHENTICATION_CANCELED = "hubidentity.error.authentication.canceled"

        const val ERROR_MESSAGE_AUTHENTICATION_CANCELED = "The user canceled to login"
    }
}