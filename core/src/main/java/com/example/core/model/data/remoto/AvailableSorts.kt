package com.example.core.model.data.remoto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableSorts(
    @SerialName(value = "id") var id: String? = null,
    @SerialName(value = "name") var name: String? = null,
)
