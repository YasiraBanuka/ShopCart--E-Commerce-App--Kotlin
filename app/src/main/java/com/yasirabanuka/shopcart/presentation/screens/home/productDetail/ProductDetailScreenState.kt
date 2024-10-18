package com.yasirabanuka.shopcart.presentation.screens.home.productDetail

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto

data class ProductDetailScreenState(
    val isLoading: Boolean = false,
    val productDetail: ProductsDto? = null,
    val addingToWishList: Boolean = false,
    val addedToWishList: Boolean = false
)
