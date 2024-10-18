package com.yasirabanuka.shopcart.presentation.screens.home.productDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.Product
import com.yasirabanuka.shopcart.domain.usecases.ProductUseCase
import com.yasirabanuka.shopcart.domain.usecases.WishListUseCase
import com.yasirabanuka.shopcart.presentation.screens.cart.CheckoutState
import com.yasirabanuka.shopcart.presentation.screens.navArgs
import com.yasirabanuka.shopcart.utils.Resource
import com.yasirabanuka.shopcart.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val wishListUseCase: WishListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var productDetailScreenState by mutableStateOf(ProductDetailScreenState())
        private set

    var checkoutState by mutableStateOf(CheckoutState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        val navArgs = savedStateHandle.navArgs<ProductDetailScreenNavArgs>()
        singleProduct(navArgs.productId)
    }

    fun onEvents(events: ProductDetailScreenEvents) {
        when(events) {
            is ProductDetailScreenEvents.AddProductToWishList -> {
                addProductToWishList(events.productsDto)
                productDetailScreenState = productDetailScreenState.copy(addingToWishList = true)
            }
            is ProductDetailScreenEvents.DeleteProductFromWishList -> {
                deleteProductFromWishList(events.product)
                productDetailScreenState = productDetailScreenState.copy(addedToWishList = false)
            }
            is ProductDetailScreenEvents.BuyNowClicked -> {
                computeTotal(events.product)
            }
        }
    }

    private fun computeTotal(product: Product) {
        viewModelScope.launch {
            eventChannel.send(UIEvents.SuccessEvent)
            checkoutState = checkoutState.copy(
                price = product.price,
                grandTotal = String.format("%.2f", product.price.toDouble() + 30.00)
            )
        }
    }

    private fun singleProduct(productId: Int) {
        productUseCase.singleProduct(productId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    productDetailScreenState = productDetailScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    productDetailScreenState = productDetailScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    productDetailScreenState = productDetailScreenState.copy(
                        isLoading = false,
                        productDetail = result.data
                    )

                    //check if product is in wishlist
                    singleProductFromWishList(productId)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addProductToWishList(productsDto: ProductsDto) {
        wishListUseCase.addProductToWishList(productsDto).onEach { result ->
            productDetailScreenState = when(result) {
                is Resource.Loading -> {
                    productDetailScreenState.copy(addingToWishList = true)
                }
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                    productDetailScreenState.copy(addingToWishList = false)
                }
                is Resource.Success -> {
                    productDetailScreenState.copy(addingToWishList = false, addedToWishList = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun singleProductFromWishList(productId: Int) {
        wishListUseCase.singleProductFromWishList(productId).onEach { result ->
            when(result) {
                is Resource.Loading -> { }
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    productDetailScreenState = if (result.data != null) {
                        productDetailScreenState.copy(addedToWishList = true)
                    } else {
                        productDetailScreenState.copy(addedToWishList = false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteProductFromWishList(product: Product) {
        wishListUseCase.deleteProductFromWishList(product).onEach { result ->
            productDetailScreenState = when(result) {
                is Resource.Loading -> {
                    productDetailScreenState.copy(addingToWishList = true)
                }
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                    productDetailScreenState.copy(addingToWishList = false)
                }
                is Resource.Success -> {
                    productDetailScreenState.copy(addingToWishList = false, addedToWishList = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}