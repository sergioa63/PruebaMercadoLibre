package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.ProductosDao
import com.example.core.model.data.local.Results

@Database(entities = [Results::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productosDao(): ProductosDao
}