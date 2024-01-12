package com.example.core.model.data.remoto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableFilters(
    @SerialName(value = "id") var id: String? = null,
    @SerialName(value = "name") var name: String? = null,
    @SerialName(value = "type") var type: String? = null,
    @SerialName(value = "values") var values: ArrayList<Values> = arrayListOf(),
)
