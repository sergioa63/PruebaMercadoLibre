package com.example.core.model.data.local

import com.example.core.model.data.remoto.AvailableFilters
import com.example.core.model.data.remoto.AvailableSorts
import com.example.core.model.data.remoto.Filters
import com.example.core.model.data.remoto.Paging
import com.example.core.model.data.remoto.Sort
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponceProductos(
    @SerialName(value = "site_id") var siteId: String? = null,
    @SerialName(value = "country_default_time_zone") var countryDefaultTimeZone: String? = null,
    @SerialName(value = "paging") var paging: Paging? = Paging(),
    @SerialName(value = "results") var results: ArrayList<Results> = arrayListOf(),
    @SerialName(value = "sort") var sort: Sort? = Sort(),
    @SerialName(value = "available_sorts") var availableSorts: ArrayList<AvailableSorts> = arrayListOf(),
    @SerialName(value = "filters") var filters: ArrayList<Filters> = arrayListOf(),
    @SerialName(value = "available_filters") var availableFilters: ArrayList<AvailableFilters> = arrayListOf(),
)
