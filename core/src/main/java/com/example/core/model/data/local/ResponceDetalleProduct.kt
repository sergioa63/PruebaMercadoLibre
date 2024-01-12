package com.example.core.model.data.local

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponceDetalleProduct(
    @SerialName(value = "text") var text: String = "",
    @SerialName(value = "plain_text") var plain_text: String = "",
    @SerialName(value = "last_updated") var last_updated: String = "",
    @SerialName(value = "date_created") var date_created: String = "",
    @SerialName(value = "snapshot") var snapshot: Snapshot = Snapshot(),
)
