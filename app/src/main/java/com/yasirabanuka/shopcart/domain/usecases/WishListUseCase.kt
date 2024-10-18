package com.yasirabanuka.shopcart.domain.usecases

import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.Product
import com.yasirabanuka.shopcart.domain.repository.WishListRepository
import com.yasirabanuka.shopcart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class WishListUseCase @Inject constructor(
    private val wishListRepository: WishListRepository,
) {

    fun addProductToWishList(productsDto: ProductsDto): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.addProductToWishList(productsDto)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to add product. Try again"))
        }
    }

    fun productsInWishList(): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.productsInWishList()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to fetch products. Try again"))
        }
    }

    fun singleProductFromWishList(productId: Int): Flow<Resource<Product?>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.singleProductFromWishList(productId)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to fetch product. Try again"))
        }
    }

    fun deleteProductFromWishList(product: Product): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val result = wishListRepository.deleteProductFromWishList(product)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to delete product. Try again"))
        }
    }
}