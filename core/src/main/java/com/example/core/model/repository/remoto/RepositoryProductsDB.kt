package com.example.core.model.repository.remoto

import com.example.core.database.dao.ProductosDao
import com.example.core.model.data.local.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryProductsDB(
    private val productosDao: ProductosDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun insertProduct(results: Results) =
        withContext(ioDispatcher) {
            /*
            val results =
                Results(
                    id = myItemCustom.id,
                    thumbnail = myItemCustom.thumb,
                    title = myItemCustom.title,
                )
             */
            if (productosDao.getProducto(results.id) == null) {
                productosDao.insertProductos(results)
            } else {
                productosDao.updateProductos(results)
            }
        }

    suspend fun getProduct(id: String) =
        withContext(ioDispatcher) {
            productosDao.getProducto(id)
        }
}
