package com.yasirabanuka.shopcart.presentation.screens.home.products

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto

/**
 * @created 10/07/2022 - 1:33 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class ProductsScreenEvents {
    data class FilterClicked(val isClicked: Boolean): ProductsScreenEvents()
    data class CategorySelected(val category: String, val index: Int): ProductsScreenEvents()
    data class AddProductToCart(val productsDto: ProductsDto): ProductsScreenEvents()
    data class DeleteProductFromCart(val productsDto: ProductsDto): ProductsScreenEvents()
}
