package com.example.core.model.repository.local

import com.example.core.database.dao.ProductosDao
import com.example.core.model.data.local.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryProducts(
    private val productosDao: ProductosDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : IReposirotyDetailLocal {
    override suspend fun insertProduct(results: Results) =
        withContext(ioDispatcher) {
            if (productosDao.getProducto(results.id) == null) {
                productosDao.insertProductos(results)
            } else {
                productosDao.updateProductos(results)
            }
        }

    override suspend fun getProduct(id: String) =
        withContext(ioDispatcher) {
            productosDao.getProducto(id)
        }
}
