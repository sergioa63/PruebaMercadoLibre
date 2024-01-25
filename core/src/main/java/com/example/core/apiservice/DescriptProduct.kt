package com.example.core.apiservice

import com.example.core.model.data.local.ResponceDetalleProduct
import retrofit2.http.GET
import retrofit2.http.Url

interface DescriptProduct {
    /**
     * Servicio GET obtener descripcion de un producto
     *
     * @param url url con Id del producto
     *
     * @return pojo ResponceDetalleProduct responce json

     */
    @GET
    suspend fun getDescripcion(
        @Url url: String,
    ): ResponceDetalleProduct
}
