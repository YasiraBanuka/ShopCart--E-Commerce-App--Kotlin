package com.yasirabanuka.shopcart.presentation.screens.auth.register


data class RegisterScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successful: Boolean = false,
    val formError: String? = null,
)
