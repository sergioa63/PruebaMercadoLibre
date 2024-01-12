package com.example.mylibrary.mainlist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mylibrary.common.navigation.AppDestinations.MAIN_ROUTE
import com.example.mylibrary.common.navigation.AppNavigationActions
import com.example.mylibrary.mainlist.MainScreen
import com.example.mylibrary.mainlist.MainViewModel

fun NavGraphBuilder.mainListScreen(
    navActions: AppNavigationActions,
    viewModel: MainViewModel,
) {
    composable(MAIN_ROUTE) {
        MainScreen(
            goToDetail = {
                navActions.navigateToTaskDetail(it)
            },
            viewModel = viewModel,
        )
    }
}
