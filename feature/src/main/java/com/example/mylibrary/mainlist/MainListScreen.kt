package com.example.mylibrary.mainlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.network.ConnectionState
import com.example.mylibrary.common.components.animations.LoadingAnimation3
import com.example.mylibrary.common.components.animations.connectivityState
import com.example.mylibrary.common.components.connection.ConnectionScreen
import com.example.mylibrary.common.components.list.ListItem
import com.example.mylibrary.common.components.tolbar.ExpandableSearchView
import com.example.mylibrary.theme.MyTestTheme
import com.example.mylibrary.theme.containerColor
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen(
    goToDetail: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    var value by rememberSaveable { mutableStateOf("") }
    val items by viewModel.itemsList.collectAsState(initial = emptyList())
    val visibleLoading by viewModel.visibleLoading.collectAsState(initial = false)
    var expanded by rememberSaveable { mutableStateOf(true) }
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    MyTestTheme {
        if (!isConnected) {
            ConnectionScreen()
        } else {
            if (visibleLoading) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.primary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LoadingAnimation3()
                }
            } else {
                Scaffold(
                    containerColor = containerColor,
                    topBar = {
                        ExpandableSearchView(
                            searchDisplay = value,
                            onSearchDisplayChanged = { newValue ->
                                value = newValue
                            },
                            onSearchDisplayClosed = {},
                            onSearchAction = {
                                viewModel.searchProduct(value)
                            },
                            onExpandedChanged = { expanded = it },
                            expanded = expanded,
                        )
                    },
                ) { padding ->
                    ListItem(listItems = items, goToDetail = {
                        viewModel.getDetailProduct(it)
                        goToDetail.invoke(it)
                    }, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}
