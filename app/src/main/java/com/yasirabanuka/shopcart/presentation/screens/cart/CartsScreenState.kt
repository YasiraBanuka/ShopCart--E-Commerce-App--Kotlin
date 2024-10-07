package com.yasirabanuka.shopcart.presentation.screens.cart

import com.yasirabanuka.shopcart.domain.model.ProductInCart

/**
 * @created 29/06/2022 - 8:50 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class CartsScreenState(
    val isLoading: Boolean = false,
    val productInCart: List<ProductInCart> = emptyList()
)
