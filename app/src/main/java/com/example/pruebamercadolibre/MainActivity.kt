package com.example.pruebamercadolibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mylibrary.common.components.list.MyListApp
import com.example.mylibrary.theme.MyTestTheme
import com.example.pruebamercadolibre.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestTheme {
                MyListApp {
                    val navController: NavHostController = rememberNavController()
                    AppNavGraph(
                        navController = navController,
                    )
                }
            }
        }
    }
}
