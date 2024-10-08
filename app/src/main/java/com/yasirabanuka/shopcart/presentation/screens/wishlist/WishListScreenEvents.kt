package com.yasirabanuka.shopcart.presentation.screens.wishlist

import com.yasirabanuka.shopcart.domain.model.Product

/**
 * @created 10/07/2022 - 1:33 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class WishListScreenEvents {
    data class DeleteClicked(val product: Product): WishListScreenEvents()
}
