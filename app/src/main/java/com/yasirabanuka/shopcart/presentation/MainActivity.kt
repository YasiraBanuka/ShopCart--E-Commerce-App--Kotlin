package com.yasirabanuka.shopcart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yasirabanuka.shopcart.presentation.components.ConnectivityStatus
import com.yasirabanuka.shopcart.presentation.components.CustomScaffold
import com.yasirabanuka.shopcart.presentation.screens.NavGraphs
import com.yasirabanuka.shopcart.presentation.screens.destinations.*
import com.yasirabanuka.shopcart.ui.theme.BuyCartTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navHostEngine = rememberNavHostEngine()
            val newBackStackEntry by navController.currentBackStackEntryAsState()
            val route = newBackStackEntry?.destination?.route
            
            BuyCartTheme {
                CustomScaffold(
                    navController = navController,
                    showBottomBar = route in listOf(
                        HomeScreenDestination.route,
                        WishListScreenDestination.route,
                        CartScreenDestination.route,
                        ProfileScreenDestination.route
                    )
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine
                        )

                        if (route != SplashScreenDestination.route) {
                            ConnectivityStatus()
                        }
                    }
                }
            }
        }
    }
}
