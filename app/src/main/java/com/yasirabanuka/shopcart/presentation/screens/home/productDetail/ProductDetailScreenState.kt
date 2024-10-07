package com.yasirabanuka.shopcart.presentation.screens.home.productDetail

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto

/**
 * @created 11/07/2022 - 6:13 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class ProductDetailScreenState(
    val isLoading: Boolean = false,
    val productDetail: ProductsDto? = null,
    val addingToWishList: Boolean = false,
    val addedToWishList: Boolean = false
)
