package com.example.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Hilt provider Dispacher IO
     *
     */
    @Provides
    fun getDispachersIo() = Dispatchers.IO
}
