package com.example.mylibrary.common.navigation

import androidx.navigation.NavHostController
import com.example.mylibrary.common.navigation.AppDestinationsArgs.ID_ARG
import com.example.mylibrary.common.navigation.AppScreens.DETAIL_SCREEN
import com.example.mylibrary.common.navigation.AppScreens.MAIN_SCREEN

private object AppScreens {
    const val MAIN_SCREEN = "main"
    const val DETAIL_SCREEN = "detail"
}

object AppDestinationsArgs {
    const val ID_ARG = "id"
}

object AppDestinations {
    const val MAIN_ROUTE = MAIN_SCREEN
    const val DETAIL_ROUTE = "$DETAIL_SCREEN/{$ID_ARG}"
}

class AppNavigationActions(private val navController: NavHostController) {
    fun navigateToTaskDetail(id: String) {
        navController.navigate("$DETAIL_SCREEN/$id")
    }
}
