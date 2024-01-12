package com.example.core.model.repository.remoto

import com.example.core.common.MainDispatcherRule
import com.example.core.database.dao.DescriptProduct
import com.example.core.di.module.ApiServiceModule
import com.example.core.model.data.local.ResponceDetalleProduct
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryDescriptionProductImplTest {
    private val descriptProductMockk = mockk<DescriptProduct>()
    lateinit var repositoryDescriptionProductImpl: RepositoryDescriptionProductImpl

    companion object {
        private const val CODE = "code"
    }

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setUp() {
        repositoryDescriptionProductImpl = RepositoryDescriptionProductImpl(descriptProductMockk)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getDetailTest() =
        runTest {
            // GIVEN
            val responseDetailProduct = ResponceDetalleProduct()
            coEvery {
                descriptProductMockk.getDescripcion("${ApiServiceModule.BASE_URL}items/$CODE/description#json")
            } returns responseDetailProduct
            // WHEN
            repositoryDescriptionProductImpl.getDetail(CODE).collect()
            advanceUntilIdle()
            // THEN
            coVerify {
                descriptProductMockk.getDescripcion("${ApiServiceModule.BASE_URL}items/$CODE/description#json")
            }
        }

    @After
    fun cleanup() {
    }
}
