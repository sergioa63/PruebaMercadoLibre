package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.model.data.local.Results

@Dao
interface ProductosDao {
    /**
     * Query en ROOM obtener listado de Productos
     *
     * @return lista de POJO Results
     */
    @Query("SELECT * FROM Results")
    fun getAllProductos(): List<Results>

    /**
     * Insert en ROOM listado de Productos
     *
     * @return Unit
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProductos(order: List<Results>)

    /**
     * Query en ROOM obtener producto
     *
     * @param id Id del producto para obtener resultado
     *
     * @return Producto en formato POJO Results
     */
    @Query("SELECT * FROM Results WHERE id = :id")
    fun getProducto(id: String): Results

    /**
     * Insert en ROOM un producto
     *
     * @param results producto en estructura POJO (Results)
     *
     * @return Unit
     */
    @Insert
    fun insertProductos(results: Results)

    /**
     * Update en ROOM producto
     *
     * @param results producto en estructura POJO (Results)
     *
     * @return Unit
     */
    @Update
    fun updateProductos(results: Results)

    /**
     * Delete en ROOM producto
     *
     * @param results producto en estructura POJO (Results)
     *
     * @return Unit
     */
    @Delete
    fun deleteProductos(results: Results)

    /**
     * Delete en ROOM todos los productos
     *
     * @return Unit
     *
     */
    @Query("DELETE FROM Results")
    fun clearProductos()
}
