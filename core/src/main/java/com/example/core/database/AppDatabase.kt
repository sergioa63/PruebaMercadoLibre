package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.ProductosDao
import com.example.core.model.data.local.Results

/**
 * Abstract class Data Base
 */
@Database(entities = [Results::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Abstract fun obtener Dao Productos
     *
     * @return ProductosDao
     */
    abstract fun productosDao(): ProductosDao
}
