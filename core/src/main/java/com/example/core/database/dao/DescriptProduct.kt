package com.example.core.database.dao

import com.example.core.model.data.local.ResponceDetalleProduct
import retrofit2.http.GET
import retrofit2.http.Url

interface DescriptProduct {
    @GET
    suspend fun getDescripcion(
        @Url url: String,
    ): ResponceDetalleProduct
}
