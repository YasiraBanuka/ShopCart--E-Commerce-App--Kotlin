package com.yasirabanuka.shopcart.data.repository

import com.yasirabanuka.shopcart.data.remote.BuyCartApi
import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.repository.ProductsRepository
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val buyCartApi: BuyCartApi,
): ProductsRepository{
    override suspend fun allProducts(): List<ProductsDto> {
        return buyCartApi.allProducts()
    }

    override suspend fun singleProduct(productId: Int): ProductsDto {
        return buyCartApi.singleProduct(productId)
    }

    override suspend fun allCategories(): List<String> {
        return buyCartApi.allCategories()
    }

    override suspend fun productsInCategory(category: String): List<ProductsDto> {
        return buyCartApi.productsInCategory(category)
    }
}