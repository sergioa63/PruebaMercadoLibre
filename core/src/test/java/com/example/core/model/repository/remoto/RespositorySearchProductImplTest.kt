package com.example.core.model.repository.remoto

import com.example.core.common.MainDispatcherRule
import com.example.core.di.module.ApiServiceModule
import com.example.core.model.data.local.ResponceProductos
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import om.example.core.apiservice.ProductosList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RespositorySearchProductImplTest {
    val productosListMockk = mockk<ProductosList>()
    lateinit var respositorySearchProductImpl: RespositorySearchProductImpl

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    companion object {
        private const val QUERY = "query"
    }

    @Before
    fun setUp() {
        respositorySearchProductImpl = RespositorySearchProductImpl(productosListMockk)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProductsTest() =
        runTest {
            // GIVEN
            val responceProductos = ResponceProductos()
            coEvery {
                productosListMockk.getProductos("${ApiServiceModule.BASE_URL}sites/MCO/search?q=$QUERY")
            } returns responceProductos
            // WHEN
            respositorySearchProductImpl.getProducts(QUERY).collect()
            advanceUntilIdle()
            // THEN
            coVerify {
                productosListMockk.getProductos("${ApiServiceModule.BASE_URL}sites/MCO/search?q=$QUERY")
            }
        }
}
