package com.yasirabanuka.shopcart.presentation.screens.cart

data class CheckoutState(
    val items: String = "1",
    val price: String = "LKR 1",
    val charges: String = "LKR 30.00",
    val grandTotal: String = "LKR 1"
)