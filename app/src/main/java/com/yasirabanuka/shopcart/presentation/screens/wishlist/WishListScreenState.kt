package com.yasirabanuka.shopcart.presentation.screens.wishlist

import com.yasirabanuka.shopcart.domain.model.Product


data class WishListScreenState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val product: Product? = null,
)
