package com.yasirabanuka.shopcart.presentation.screens.home.products

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.ProductInCart

/**
 * @created 29/06/2022 - 8:50 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class ProductsScreenState(
    val isLoading: Boolean = false,
    val filterSelected: Boolean = false,
    val selectedCategoryIndex: Int = 0,
    val categories: MutableList<String> = mutableListOf(),
    val products: List<ProductsDto> = emptyList(),
    val productInCart: List<ProductInCart> = emptyList(),
    val addingToCart: Boolean = false,
)
