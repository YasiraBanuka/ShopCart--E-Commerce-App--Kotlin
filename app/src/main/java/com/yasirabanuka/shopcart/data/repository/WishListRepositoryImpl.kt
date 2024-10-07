package com.yasirabanuka.shopcart.data.repository

import com.yasirabanuka.shopcart.data.local.BuyCartDao
import com.yasirabanuka.shopcart.data.mapper.toProduct
import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.Product
import com.yasirabanuka.shopcart.domain.repository.WishListRepository
import javax.inject.Inject

/**
 * @created 28/06/2022 - 8:12 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class WishListRepositoryImpl @Inject constructor(
    private val buyCartDao: BuyCartDao
): WishListRepository{
    override suspend fun addProductToWishList(productsDto: ProductsDto) {
        return buyCartDao.addProductToWishList(productsDto.toProduct())
    }

    override suspend fun productsInWishList(): List<Product> {
        return buyCartDao.getProductsInWishList()
    }

    override suspend fun singleProductFromWishList(productId: Int): Product? {
        return buyCartDao.singleProductFromWishList(productId)
    }

    override suspend fun deleteProductFromWishList(product: Product) {
        return buyCartDao.deleteFromWishList(product)
    }
}