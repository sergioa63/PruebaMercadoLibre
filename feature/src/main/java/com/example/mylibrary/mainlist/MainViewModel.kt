package com.example.mylibrary.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.data.local.Results
import com.example.core.model.repository.local.IReposirotyDetailLocal
import com.example.core.model.repository.remoto.IRepositoryDetailRemote
import com.example.core.model.repository.remoto.IRepositoryListRemote
import com.example.core.network.clearThousandFormat
import com.example.core.network.formatThousand
import com.example.mylibrary.model.MyItemCustom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class
MainViewModel
    @Inject
    constructor(
        private val repositorySearchProduct: IRepositoryListRemote,
        private val repositoryDescriptProduct: IRepositoryDetailRemote,
        private val repositoryProductsDB: IReposirotyDetailLocal,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) : ViewModel() {
        private val _itemsList = MutableStateFlow(emptyList<MyItemCustom>())
        val itemsList: StateFlow<List<MyItemCustom>> = _itemsList

        private val _item = MutableStateFlow(MyItemCustom())
        val item: StateFlow<MyItemCustom> = _item

        private val _visibleLoading = MutableStateFlow(false)
        val visibleLoading: StateFlow<Boolean> = _visibleLoading

        fun getItemSelect(id: String): MyItemCustom {
            _item.value =
                _itemsList.value.first {
                    it.id == id
                }
            return _item.value
        }

        fun searchProduct(query: String) {
            _visibleLoading.value = true
            viewModelScope.launch {
                repositorySearchProduct.getProducts(query)
                    .flowOn(ioDispatcher)
                    .catch { e ->
                        _visibleLoading.value = false
                    }
                    .collect {
                        _itemsList.value =
                            it.results.map {
                                MyItemCustom(
                                    id = it.id,
                                    title = it.title,
                                    thumb = it.thumbnail,
                                    price = it.price.formatThousand().clearThousandFormat(),
                                    selected = isAddProduct(it.id),
                                )
                            }
                        _visibleLoading.value = false
                    }
            }
        }

        fun getDetailProduct(code: String) {
            _visibleLoading.value = true
            viewModelScope.launch {
                repositoryDescriptProduct.getDetail(code)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        _visibleLoading.value = false
                    }
                    .collect {
                        it.plain_text.let {
                            _item.value.descrip = it
                        }
                        _visibleLoading.value = false
                    }
            }
        }

        fun addProduct() {
            viewModelScope.launch {
                item.value.let {
                    val results =
                        Results(
                            id = it.id,
                            thumbnail = it.thumb,
                            title = it.title,
                        )
                    repositoryProductsDB.insertProduct(results)
                }
            }
            if (_itemsList.value.isNotEmpty()) {
                _itemsList.value.first {
                    it.id == _item.value.id
                }.selected = true
            }
        }

        private suspend fun isAddProduct(id: String): Boolean {
            return repositoryProductsDB.getProduct(id) != null
        }
    }
