package com.yasirabanuka.shopcart.data.remote.dto.request


data class UserDto(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val phone: String
)
