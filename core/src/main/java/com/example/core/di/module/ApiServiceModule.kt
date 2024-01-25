package com.example.core.di.module

import com.example.core.apiservice.DescriptProduct
import com.example.core.model.repository.remoto.IRepositoryDetailRemote
import com.example.core.model.repository.remoto.IRepositoryListRemote
import com.example.core.model.repository.remoto.RepositoryDescriptionProductImpl
import com.example.core.model.repository.remoto.RespositorySearchProductImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import om.example.core.apiservice.ProductosList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    const val BASE_URL = "https://api.mercadolibre.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideDescriptProduct(retrofit: Retrofit): DescriptProduct =
        retrofit.create(
            DescriptProduct::class.java,
        )

    @Singleton
    @Provides
    fun provideProductosList(retrofit: Retrofit): ProductosList = retrofit.create(ProductosList::class.java)

    @Singleton
    @Provides
    fun providesRepositoryDescriptionProductImpl(descriptProduct: DescriptProduct): IRepositoryDetailRemote =
        RepositoryDescriptionProductImpl(descriptProduct)

    @Singleton
    @Provides
    fun providesRespositorySearchProductImpl(productosList: ProductosList): IRepositoryListRemote =
        RespositorySearchProductImpl(productosList)
}
