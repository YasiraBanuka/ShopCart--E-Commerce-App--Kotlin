package com.yasirabanuka.shopcart.presentation.screens.wishlist

import com.yasirabanuka.shopcart.domain.model.Product

sealed class WishListScreenEvents {
    data class DeleteClicked(val product: Product): WishListScreenEvents()
}
