package com.yasirabanuka.shopcart.presentation.screens.home.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasirabanuka.shopcart.data.local.SharedPrefUtil
import com.yasirabanuka.shopcart.data.mapper.toProductInCart
import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import com.yasirabanuka.shopcart.domain.model.UserProfile
import com.yasirabanuka.shopcart.domain.usecases.CartUseCase
import com.yasirabanuka.shopcart.domain.usecases.ProductUseCase
import com.yasirabanuka.shopcart.domain.usecases.ProfileUseCase
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
class ProductsViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val cartUseCase: CartUseCase,
    private val profileUseCase: ProfileUseCase,
    private val sharedPrefUtil: SharedPrefUtil,
) : ViewModel() {

    var productsScreenState by mutableStateOf(ProductsScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()


    init {
        if (!sharedPrefUtil.userIsLoggedIn()) {
            viewModelScope.launch {
                eventChannel.send(UIEvents.SuccessEvent)
            }
        }
        fetchCategories()
        fetchProducts()
        usersCart()
        fetchUserProfile()
    }

    val offerImages = listOf(
        "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/offerImage1.jpg",
        "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/offerImage2.jpg",
        "https://github.com/HenryUdorji/BuyCart/raw/master/offersImages/offerImage3.jpg",
    )

    fun onEvents(events: ProductsScreenEvents) {
        when (events) {
            is ProductsScreenEvents.FilterClicked -> {
                productsScreenState = productsScreenState.copy(filterSelected = events.isClicked)
            }
            is ProductsScreenEvents.CategorySelected -> {
                fetchProducts(events.category)
                productsScreenState = productsScreenState.copy(selectedCategoryIndex = events.index)
            }
            is ProductsScreenEvents.AddProductToCart -> {
                addProductToCart(events.productsDto)
            }
            is ProductsScreenEvents.DeleteProductFromCart -> {
                deleteProductToCart(events.productsDto)
            }
        }
    }

    private fun fetchProducts(category: String = "All") {
        productUseCase.products(category).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    productsScreenState = productsScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    productsScreenState = productsScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    productsScreenState = productsScreenState.copy(isLoading = false)
                    productsScreenState =
                        productsScreenState.copy(products = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchCategories() {
        productUseCase.categories().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    productsScreenState = productsScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    productsScreenState = productsScreenState.copy(isLoading = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    val toMutableList = result.data?.toMutableList()
                    toMutableList?.add(0, "All")
                    productsScreenState = productsScreenState.copy(categories = (toMutableList!!))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addProductToCart(productsDto: ProductsDto) {
        cartUseCase.addProductToCart(productsDto.toProductInCart()).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    productsScreenState = productsScreenState.copy(addingToCart = true)
                }
                is Resource.Error -> {
                    productsScreenState = productsScreenState.copy(addingToCart = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    usersCart()
                    productsScreenState = productsScreenState.copy(addingToCart = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteProductToCart(productsDto: ProductsDto) {
        cartUseCase.deleteProductInCart(productsDto.toProductInCart()).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    productsScreenState = productsScreenState.copy(addingToCart = true)
                }
                is Resource.Error -> {
                    productsScreenState = productsScreenState.copy(addingToCart = false)
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    usersCart()
                    productsScreenState = productsScreenState.copy(addingToCart = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun usersCart() {
        cartUseCase.usersCart().onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    productsScreenState =
                        productsScreenState.copy(productInCart = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchUserProfile() {
        profileUseCase.userProfile(1).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    saveUserProfile(result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveUserProfile(userProfile: UserProfile) {
        profileUseCase.saveUserProfile(userProfile).onEach { result ->
            if (!result) {
                //eventChannel.send(UIEvents.ErrorEvent("Failed to save user profile"))
            }
        }.launchIn(viewModelScope)
    }
}