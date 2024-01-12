package com.example.pruebamercadolibre.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mylibrary.common.navigation.AppDestinations.MAIN_ROUTE
import com.example.mylibrary.common.navigation.AppNavigationActions
import com.example.mylibrary.detail.navigation.detailScreen
import com.example.mylibrary.mainlist.MainViewModel
import com.example.mylibrary.mainlist.navigation.mainListScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = MAIN_ROUTE,
    navActions: AppNavigationActions =
        remember(navController) {
            AppNavigationActions(navController)
        },
) {
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mainListScreen(navActions, viewModel)
        detailScreen(navController, viewModel)
    }
}
