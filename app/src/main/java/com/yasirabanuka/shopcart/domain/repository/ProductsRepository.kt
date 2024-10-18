package com.yasirabanuka.shopcart.domain.repository

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto

interface ProductsRepository {
    suspend fun allProducts(): List<ProductsDto>
    suspend fun singleProduct(productId: Int): ProductsDto
    suspend fun allCategories(): List<String>
    suspend fun productsInCategory(category: String): List<ProductsDto>
}