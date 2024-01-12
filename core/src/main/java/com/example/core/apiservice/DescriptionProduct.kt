package com.example.core.apiservice

import com.example.core.model.data.local.ResponceDetalleProduct
import retrofit2.http.GET
import retrofit2.http.Url

interface DescriptionProduct {
    @GET
    suspend fun getDescription(
        @Url url: String,
    ): ResponceDetalleProduct
}
