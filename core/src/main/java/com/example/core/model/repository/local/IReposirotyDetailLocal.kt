package com.example.core.model.repository.local

import com.example.core.model.data.local.Results

interface IReposirotyDetailLocal {
    suspend fun insertProduct(results: Results)

    suspend fun getProduct(id: String): Results
}
