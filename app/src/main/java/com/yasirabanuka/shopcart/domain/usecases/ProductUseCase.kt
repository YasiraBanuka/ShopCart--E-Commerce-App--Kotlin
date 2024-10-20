package com.yasirabanuka.shopcart.domain.usecases

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.repository.ProductsRepository
import com.yasirabanuka.shopcart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
) {

    fun categories(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val response = productsRepository.allCategories()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun products(category: String): Flow<Resource<List<ProductsDto>>> = flow {
        try {
            emit(Resource.Loading())
            val response = if (category == "All") {
                productsRepository.allProducts()
            } else {
                productsRepository.productsInCategory(category)
            }
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun singleProduct(productId: Int): Flow<Resource<ProductsDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = productsRepository.singleProduct(productId)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}