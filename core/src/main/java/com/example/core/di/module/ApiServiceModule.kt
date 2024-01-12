package com.example.core.di.module

import com.example.core.database.dao.DescriptProduct
import com.example.core.model.repository.local.RepositoryDescriptionProduct
import com.example.core.model.repository.remoto.RespositorySearchProduct
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
    fun provideDescriptProduct(retrofit: Retrofit): DescriptProduct = retrofit.create(DescriptProduct::class.java)

    @Singleton
    @Provides
    fun provideProductosList(retrofit: Retrofit): ProductosList = retrofit.create(ProductosList::class.java)

    @Singleton
    @Provides
    fun providesRepositoryDescriptionProduct(descriptProduct: DescriptProduct) = RepositoryDescriptionProduct(descriptProduct)

    @Singleton
    @Provides
    fun providesRespositorySearchProduct(productosList: ProductosList) = RespositorySearchProduct(productosList)
}
