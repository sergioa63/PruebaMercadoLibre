package com.example.core.model.repository.local

import com.example.core.database.dao.DescriptProduct
import com.example.core.di.module.ApiServiceModule.BASE_URL
import com.example.core.model.data.local.ResponceDetalleProduct
import kotlinx.coroutines.flow.flow

class RepositoryDescriptionProduct(private val descriptProduct: DescriptProduct) {
    suspend fun getDetail(code: String) =
        flow<ResponceDetalleProduct> {
            val response =
                descriptProduct.getDescripcion(
                    "${BASE_URL}items/$code/description#json",
                )
            emit(response)
        }
}
