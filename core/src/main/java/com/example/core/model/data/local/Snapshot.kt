package com.example.core.model.data.local
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snapshot(
    @SerialName(value = "url") var url: String? = null,
    @SerialName(value = "width") var width: Int? = null,
    @SerialName(value = "height") var height: Int? = null,
    @SerialName(value = "status") var status: String? = null,
)
