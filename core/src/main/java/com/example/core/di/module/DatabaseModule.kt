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

    @Provides
    fun provideProductosDao(appDatabase: AppDatabase): ProductosDao {
        return appDatabase.productosDao()
    }

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

    @Singleton
    @Provides
    fun providesRepositoryProducts(productosDao: ProductosDao): IReposirotyDetailLocal = RepositoryProducts(productosDao)
}
