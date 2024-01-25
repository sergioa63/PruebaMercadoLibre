package com.example.core.model.repository.remoto

import com.example.core.model.data.local.ResponceDetalleProduct
import kotlinx.coroutines.flow.Flow

interface IRepositoryDetailRemote {
    /**
     *  Obtener descripcion de un producto
     *
     * @param code el Id del producto
     */
    suspend fun getDetail(code: String): Flow<ResponceDetalleProduct>
}
