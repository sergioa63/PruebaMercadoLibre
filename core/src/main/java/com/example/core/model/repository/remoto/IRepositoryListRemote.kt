package com.example.core.model.repository.remoto

import com.example.core.model.data.local.ResponceProductos
import kotlinx.coroutines.flow.Flow

interface IRepositoryListRemote {
    /**
     * Obtener descripcion de un producto
     *
     * @param query url con Query
     */
    suspend fun getProducts(query: String): Flow<ResponceProductos>
}
