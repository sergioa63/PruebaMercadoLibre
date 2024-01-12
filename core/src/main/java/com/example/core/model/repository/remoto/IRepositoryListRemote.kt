package com.example.core.model.repository.remoto

import com.example.core.model.data.local.ResponceProductos
import kotlinx.coroutines.flow.Flow

interface IRepositoryListRemote {
    suspend fun getProducts(query: String): Flow<ResponceProductos>
}
