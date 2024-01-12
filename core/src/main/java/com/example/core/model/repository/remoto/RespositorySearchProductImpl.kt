package com.example.core.model.repository.remoto

import com.example.core.di.module.ApiServiceModule.BASE_URL
import com.example.core.model.data.local.ResponceProductos
import kotlinx.coroutines.flow.flow
import om.example.core.apiservice.ProductosList

class RespositorySearchProductImpl(private val productosList: ProductosList) : IRepositoryListRemote {
    override suspend fun getProducts(query: String) =
        flow<ResponceProductos> {
            val response = productosList.getProductos("${BASE_URL}sites/MCO/search?q=$query")
            emit(response)
        }
}
