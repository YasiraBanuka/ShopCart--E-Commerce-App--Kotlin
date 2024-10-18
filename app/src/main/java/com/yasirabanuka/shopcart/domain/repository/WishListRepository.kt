package com.yasirabanuka.shopcart.domain.repository

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.Product

interface WishListRepository {
    suspend fun addProductToWishList(productsDto: ProductsDto)
    suspend fun productsInWishList(): List<Product>
    suspend fun singleProductFromWishList(productId: Int): Product?
    suspend fun deleteProductFromWishList(product: Product)
}