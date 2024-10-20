package com.yasirabanuka.shopcart.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yasirabanuka.shopcart.domain.model.PaymentInfo
import com.yasirabanuka.shopcart.presentation.components.CustomTextField
import com.yasirabanuka.shopcart.presentation.components.CustomToolbar
import com.yasirabanuka.shopcart.presentation.components.DateVisualTransformation
import com.yasirabanuka.shopcart.ui.theme.backgroundColor
import com.yasirabanuka.shopcart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun PaymentInfoScreen(
    navigator: DestinationsNavigator,
    paymentInfo: PaymentInfo?,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        profileViewModel.eventChannelFlow.collectLatest { uiEvents ->
            when (uiEvents) {
                is UIEvents.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        uiEvents.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is UIEvents.SuccessEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Payment Information saved",
                        duration = SnackbarDuration.Short
                    )
//                    navigator.popBackStack()
//                    navigator.navigate(LoginScreenDestination)

//                                val openDialog = remember { mutableStateOf(true) }
//                                if (openDialog.value) {
//                                    CustomAlertDialog(onDismissRequest = {
//                                        openDialog.value = false
//                                        navigator.popBackStack()
//                                        navigator.navigate(LoginScreenDestination)
//                                    })
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomToolbar(title = "Credit/Debit Card") { navigator.navigateUp() }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                FormContentSection(paymentInfo, profileViewModel)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormContentSection(
    paymentInfo: PaymentInfo?,
    profileViewModel: ProfileViewModel
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var cardNumber by remember { mutableStateOf(paymentInfo?.cardNumber ?: "") }
    var cardName by remember { mutableStateOf(paymentInfo?.cardHolderName ?: "") }
    var cardExpiryDate by remember { mutableStateOf(paymentInfo?.cardExpiryDate ?: "") }
    var cardCVV by remember { mutableStateOf(paymentInfo?.cardCVV ?: "") }

    val maxCVV = 3
    val maxCardNumber = 16


    CustomTextField(
        label = "Card Number",
        text = cardNumber,
        placeholder = "Enter Card Number",
        keyboardType = KeyboardType.Number,
        onValueChange = {
            cardNumber = it.take(maxCardNumber)
            if (it.length > maxCardNumber) {
                focusManager.moveFocus(FocusDirection.Down)
            }
        }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "CardHolder Name",
        text = cardName,
        placeholder = "Enter CardHolder Name",
        onValueChange = { cardName = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "Card Expiry",
        text = cardExpiryDate,
        placeholder = "DD-MM-yyyy",
        visualTransformation = DateVisualTransformation(),
        keyboardType = KeyboardType.Number,
        onValueChange = { cardExpiryDate = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "Card CVV",
        text = cardCVV,
        placeholder = "Enter Card CVV",
        keyboardType = KeyboardType.Number,
        onValueChange = {
            cardCVV = it.take(maxCVV)
        }) {
        keyboardController?.hide()
    }

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            val payInfo = paymentInfo?.copy(
                cardNumber = cardNumber,
                cardHolderName = cardName,
                cardExpiryDate = cardExpiryDate,
                cardCVV = cardCVV
            )
            profileViewModel.onEvent(PaymentInfoEvents.SaveCard(payInfo))
        }) {
        Text(text = "Save Card", style = MaterialTheme.typography.button)
    }
}
