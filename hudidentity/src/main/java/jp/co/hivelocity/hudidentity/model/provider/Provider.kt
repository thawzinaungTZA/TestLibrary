package jp.co.hivelocity.hudidentity.model.provider

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Provider(
    @SerializedName("name") var name: String,
    @SerializedName("request_url") val request_url: String
) : Serializable