package com.example.core.model.data.remoto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Paging(
    @SerialName(value = "total") var total: Int? = null,
    @SerialName(value = "primary_results") var primaryResults: Int? = null,
    @SerialName(value = "offset") var offset: Int? = null,
    @SerialName(value = "limit") var limit: Int? = null,
)
