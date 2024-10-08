package com.yasirabanuka.shopcart.data.remote.dto.request

/**
 * @created 28/06/2022 - 4:23 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class UserDto(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val phone: String
)
