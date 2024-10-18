package com.yasirabanuka.shopcart.presentation.screens.cart

import com.yasirabanuka.shopcart.domain.model.ProductInCart


data class CartsScreenState(
    val isLoading: Boolean = false,
    val productInCart: List<ProductInCart> = emptyList()
)
