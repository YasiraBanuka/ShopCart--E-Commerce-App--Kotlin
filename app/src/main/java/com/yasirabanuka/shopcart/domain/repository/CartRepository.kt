package com.yasirabanuka.shopcart.domain.repository

import com.yasirabanuka.shopcart.domain.model.ProductInCart


interface CartRepository {
    suspend fun usersCart(): List<ProductInCart>
    suspend fun addProductToCart(productInCart: ProductInCart)
    suspend fun updateProductInCart(productInCart: ProductInCart)
    suspend fun deleteProductFromCart(productInCart: ProductInCart)
}