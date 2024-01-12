package com.example.mylibrary.mainlist

import com.example.core.model.data.local.ResponceProductos
import com.example.core.model.data.local.Results
import com.example.core.model.repository.local.RepositoryProducts
import com.example.core.model.repository.remoto.RepositoryDescriptionProductImpl
import com.example.core.model.repository.remoto.RespositorySearchProductImpl
import com.example.mylibrary.common.MainDispatcherRule
import com.example.mylibrary.model.MyItemCustom
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    lateinit var mainViewModel: MainViewModel
    val repositoryListRemoteMockk = mockk<RespositorySearchProductImpl>()
    val repositoryDescriptionProductMockk = mockk<RepositoryDescriptionProductImpl>()
    val repositoryProductsMockk = mockk<RepositoryProducts>()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    companion object {
        private const val QUERY = "query"
    }

    @Before
    fun setUp() {
        mainViewModel =
            MainViewModel(
                repositoryListRemoteMockk,
                repositoryDescriptionProductMockk,
                repositoryProductsMockk,
            )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenInsertProductInvoke_RepositoryProducts() =
        runTest {
            // GIVEN
            val myItemCustom = MyItemCustom()
            val results =
                Results(
                    id = myItemCustom.id,
                    thumbnail = myItemCustom.thumb,
                    title = myItemCustom.title,
                )
            coEvery {
                repositoryProductsMockk.insertProduct(results)
            } returns Unit
            // WHEN
            mainViewModel.addProduct()
            advanceUntilIdle()
            // THEN
            coVerify {
                repositoryProductsMockk.insertProduct(results)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchProductTest_invokeRepositoryListRemote() =
        runTest {
            // GIVEN
            val responceProducts = ResponceProductos()
            coEvery {
                repositoryListRemoteMockk.getProducts(QUERY)
            } returns flow { responceProducts }
            // WHEN
            mainViewModel.searchProduct(QUERY)
            advanceUntilIdle()
            // THEN
            coVerify { repositoryListRemoteMockk.getProducts(QUERY) }
        }

    @After
    fun cleanup() {
    }
}
