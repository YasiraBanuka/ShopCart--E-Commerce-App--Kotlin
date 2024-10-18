package com.yasirabanuka.shopcart.presentation.screens.auth


data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)