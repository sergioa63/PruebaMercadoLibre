package com.example.core.model.repository.local

import com.example.core.common.MainDispatcherRule
import com.example.core.database.dao.ProductosDao
import com.example.core.model.data.local.Results
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryProductsTest {
    private val productosDaoMockk = mockk<ProductosDao>()

    lateinit var repositoryProductsMockk: RepositoryProducts

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    companion object {
        private const val ID = "id"
    }

    @Before
    fun setUp() {
        repositoryProductsMockk = RepositoryProducts(productosDaoMockk)
    }

    @Test
    fun whenGetProductInvoke_verifyResult() =
        runTest {
            // GIVEN
            val results = Results()
            coEvery {
                productosDaoMockk.getProducto(ID)
            } returns results
            // WHEN
            val result = repositoryProductsMockk.getProduct(ID)
            // THEN
            assert(result == results)
            coVerify { productosDaoMockk.getProducto(ID) }
        }

    @After
    fun cleanup() {
    }
}
