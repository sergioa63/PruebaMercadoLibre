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
    @Query("SELECT * FROM Results")
    fun getAllProductos(): List<Results>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProductos(order: List<Results?>?)

    @Query("SELECT * FROM Results WHERE id = :id")
    fun getProducto(id: String): Results

    @Insert
    fun insertProductos(results: Results)

    @Update
    fun updateProductos(results: Results)

    @Delete
    fun deleteProductos(results: Results)

    @Query("DELETE FROM Results")
    fun clearProductos()
}
