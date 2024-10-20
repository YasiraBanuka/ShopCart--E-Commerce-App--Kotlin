package com.yasirabanuka.shopcart.presentation.screens.home.productDetail

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.Product


sealed class ProductDetailScreenEvents {
    data class AddProductToWishList(val productsDto: ProductsDto): ProductDetailScreenEvents()
    data class DeleteProductFromWishList(val product: Product): ProductDetailScreenEvents()
    data class BuyNowClicked(val product: Product): ProductDetailScreenEvents()
}
