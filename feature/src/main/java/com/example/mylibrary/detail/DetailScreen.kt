package com.example.mylibrary.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.network.ConnectionState
import com.example.mylibrary.R
import com.example.mylibrary.common.components.animations.connectivityState
import com.example.mylibrary.common.components.buttons.ButtonAddWithIcon
import com.example.mylibrary.common.components.connection.ConnectionScreen
import com.example.mylibrary.common.components.list.DescriptItem
import com.example.mylibrary.common.components.list.MyItem
import com.example.mylibrary.common.components.list.TitleItem
import com.example.mylibrary.mainlist.MainViewModel
import com.example.mylibrary.theme.MyTestTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
    ExperimentalCoroutinesApi::class,
)
@Composable
fun DetailScreen(
    id: String,
    onBack: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val item = remember { viewModel.getItemSelect(id) }
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
    val focusManager = LocalFocusManager.current
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    MyTestTheme {
        if (!isConnected) {
            ConnectionScreen()
        } else {
            SideEffect {
                focusManager.clearFocus()
            }
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = { Text(text = stringResource(R.string.detail)) },
                        colors =
                            TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                scrolledContainerColor = MaterialTheme.colorScheme.primary,
                                navigationIconContentColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.background,
                            ),
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.menu_back))
                            }
                        },
                    )
                },
                floatingActionButton = {
                    ButtonAddWithIcon {
                        viewModel.addProduct()
                        onBack()
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
                content = { padding ->
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(padding)
                                .background(MaterialTheme.colorScheme.tertiary),
                    ) {
                        Card(
                            colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                ),
                            border =
                                BorderStroke(
                                    dimensionResource(id = R.dimen.padding_2dp),
                                    Color.LightGray,
                                ),
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(dimensionResource(id = R.dimen.padding_8dp))
                                    .fillMaxSize(),
                        ) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxSize()
                                        .verticalScroll(rememberScrollState())
                                        .height(IntrinsicSize.Max),
                            ) {
                                MyItem(
                                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_8dp)),
                                    item = item,
                                )
                                TitleItem(item = item)
                                DescriptItem(item = item, Modifier)
                            }
                        }
                    }
                },
            )
        }
    }
}
