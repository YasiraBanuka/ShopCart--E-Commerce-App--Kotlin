package com.yasirabanuka.shopcart.presentation.screens.auth.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yasirabanuka.shopcart.R
import com.yasirabanuka.shopcart.presentation.screens.auth.AuthViewModel
import com.yasirabanuka.shopcart.presentation.screens.destinations.HomeScreenDestination
import com.yasirabanuka.shopcart.presentation.screens.destinations.OnBoardingScreenDestination
import com.yasirabanuka.shopcart.ui.theme.primaryColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay


@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<AuthViewModel>()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(primaryColor)
        systemUiController.setNavigationBarColor(primaryColor)
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        if (viewModel.isFirstAppLaunch) {
            navigator.popBackStack()
            navigator.navigate(OnBoardingScreenDestination)
        } else {
            navigator.popBackStack()
            navigator.navigate(HomeScreenDestination)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(primaryColor)) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}