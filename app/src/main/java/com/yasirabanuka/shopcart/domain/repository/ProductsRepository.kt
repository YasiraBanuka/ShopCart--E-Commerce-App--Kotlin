package com.yasirabanuka.shopcart.domain.repository

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto

/**
 * @created 28/06/2022 - 8:02 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
interface ProductsRepository {
    suspend fun allProducts(): List<ProductsDto>
    suspend fun singleProduct(productId: Int): ProductsDto
    suspend fun allCategories(): List<String>
    suspend fun productsInCategory(category: String): List<ProductsDto>
}