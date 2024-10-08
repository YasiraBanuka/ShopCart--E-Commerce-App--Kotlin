package com.yasirabanuka.shopcart.presentation.screens.auth.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yasirabanuka.shopcart.R
import com.yasirabanuka.shopcart.presentation.screens.destinations.LoginScreenDestination
import com.yasirabanuka.shopcart.presentation.screens.destinations.RegisterScreenDestination
import com.yasirabanuka.shopcart.ui.theme.welcomeStatusBarColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(welcomeStatusBarColor)
        systemUiController.setNavigationBarColor(welcomeStatusBarColor)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.welcome_image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().align(Alignment.TopCenter),
            contentScale = ContentScale.Inside
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = { navigator.navigate(LoginScreenDestination) }
            ) {
                Text(text = "LOGIN", style = MaterialTheme.typography.button)
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedButton(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent
                ),
                onClick = { navigator.navigate(RegisterScreenDestination) }
            ) {
                Text(text = "REGISTER", style = MaterialTheme.typography.button)
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}