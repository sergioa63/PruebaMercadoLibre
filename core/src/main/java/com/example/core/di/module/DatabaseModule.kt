package com.example.core.di.module

import android.content.Context
import androidx.room.Room
import com.example.core.database.AppDatabase
import com.example.core.database.dao.ProductosDao
import com.example.core.model.repository.local.IReposirotyDetailLocal
import com.example.core.model.repository.local.RepositoryProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    companion object {
        private const val NAME_DATA_DASE = "database_test"
    }

    /**
     * Hilt provider Productos Dao
     *
     * @param appDatabase set Database
     *
     * @return Producto Dao
     */
    @Provides
    fun provideProductosDao(appDatabase: AppDatabase): ProductosDao {
        return appDatabase.productosDao()
    }

    /**
     * Hilt provider DataBase Singleton
     *
     * @param appContext Context de aplicacion
     *
     * @return AppDatabase
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            NAME_DATA_DASE,
        ).build()
    }

    /**
     * Hilt provider IRepositoryProducts
     *
     * @param productosDao Dao Productos
     *
     * @return Implementacion RepositoryProducts
     */
    @Singleton
    @Provides
    fun providesRepositoryProducts(productosDao: ProductosDao): IReposirotyDetailLocal = RepositoryProducts(productosDao)
}
