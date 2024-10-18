package com.yasirabanuka.shopcart.presentation.screens.home.products

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.ProductInCart


data class ProductsScreenState(
    val isLoading: Boolean = false,
    val filterSelected: Boolean = false,
    val selectedCategoryIndex: Int = 0,
    val categories: MutableList<String> = mutableListOf(),
    val products: List<ProductsDto> = emptyList(),
    val productInCart: List<ProductInCart> = emptyList(),
    val addingToCart: Boolean = false,
)
