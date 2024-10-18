package com.yasirabanuka.shopcart.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.yasirabanuka.shopcart.ui.theme.backgroundColor


@Composable
fun CustomScaffold(
    navController: NavController,
    showBottomBar: Boolean = true,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        backgroundColor = backgroundColor,
        bottomBar = {
            if (showBottomBar) {
                CustomBottomNavBar(navController = navController)
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}