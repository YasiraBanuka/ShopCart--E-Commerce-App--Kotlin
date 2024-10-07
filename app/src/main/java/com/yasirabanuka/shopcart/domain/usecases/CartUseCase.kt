package com.yasirabanuka.shopcart.domain.usecases

import com.yasirabanuka.shopcart.domain.model.ProductInCart
import com.yasirabanuka.shopcart.domain.repository.CartRepository
import com.yasirabanuka.shopcart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @created 10/07/2022 - 8:49 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

class CartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    fun addProductToCart(productInCart: ProductInCart): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = cartRepository.addProductToCart(productInCart)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to add product to cart"))
        }
    }

    fun deleteProductInCart(productInCart: ProductInCart): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = cartRepository.deleteProductFromCart(productInCart)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to delete product from cart"))
        }
    }

    fun updateProductInCart(productInCart: ProductInCart): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = cartRepository.updateProductInCart(productInCart)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to update product in cart"))
        }
    }

    fun usersCart(): Flow<Resource<List<ProductInCart>>> = flow {
        try {
            emit(Resource.Loading())
            val response = cartRepository.usersCart()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unable to fetch cart"))
        }
    }
}