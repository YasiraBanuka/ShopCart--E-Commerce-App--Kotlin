package com.yasirabanuka.shopcart.presentation.screens.auth


sealed class AuthScreenEvents {
    data class LoginClicked(val username: String, val password: String) : AuthScreenEvents()
    data class RegisterClicked(
        val username: String,
        val password: String,
        val email: String,
        val phoneNo: String
    ) : AuthScreenEvents()
}
