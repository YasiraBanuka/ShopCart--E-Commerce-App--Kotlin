package com.yasirabanuka.shopcart.data.repository

import com.yasirabanuka.shopcart.data.local.BuyCartDao
import com.yasirabanuka.shopcart.domain.model.ProductInCart
import com.yasirabanuka.shopcart.domain.repository.CartRepository
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val buyCartDao: BuyCartDao
): CartRepository{
    override suspend fun usersCart(): List<ProductInCart> {
        return buyCartDao.productsInCart()
    }

    override suspend fun addProductToCart(productInCart: ProductInCart) {
        return buyCartDao.addProductToCart(productInCart)
    }

    override suspend fun updateProductInCart(productInCart: ProductInCart) {
        return buyCartDao.updateProductInCart(productInCart)
    }

    override suspend fun deleteProductFromCart(productInCart: ProductInCart) {
        return buyCartDao.deleteProductFromCart(productInCart)
    }

}