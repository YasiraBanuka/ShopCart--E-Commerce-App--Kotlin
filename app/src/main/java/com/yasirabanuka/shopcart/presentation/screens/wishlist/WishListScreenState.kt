package com.yasirabanuka.shopcart.presentation.screens.wishlist

import com.yasirabanuka.shopcart.domain.model.Product

/**
 * @created 29/06/2022 - 8:50 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class WishListScreenState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val product: Product? = null,
)
