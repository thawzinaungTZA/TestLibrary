package jp.co.hivelocity.hudidentity.model.provider

enum class Providers(val provider_name: String, val label: String) {
    FACEBOOK("facebook", "Facebook")
    , GOOGLE("google", "Google")
    , TWITTER("twitter", "Twitter")
    , LINE("line", "Line")
}