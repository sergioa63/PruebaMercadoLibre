package com.example.mylibrary.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mylibrary.common.navigation.AppDestinations.DETAIL_ROUTE
import com.example.mylibrary.common.navigation.AppDestinationsArgs.ID_ARG
import com.example.mylibrary.detail.DetailScreen
import com.example.mylibrary.mainlist.MainViewModel

fun NavGraphBuilder.detailScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    composable(
        route = DETAIL_ROUTE,
        arguments =
            listOf(
                navArgument(ID_ARG) {
                    type = NavType.StringType
                },
            ),
    ) { entry ->
        val idArgument = entry.arguments?.getString(ID_ARG)
        requireNotNull(idArgument)
        DetailScreen(
            id = idArgument,
            onBack =
                {
                    navController.popBackStack()
                },
            viewModel = viewModel,
        )
    }
}
