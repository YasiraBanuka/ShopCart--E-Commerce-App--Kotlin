package com.yasirabanuka.shopcart.domain.repository

import com.yasirabanuka.shopcart.data.remote.dto.request.LoginDto
import com.yasirabanuka.shopcart.data.remote.dto.request.ProfileDto
import com.yasirabanuka.shopcart.data.remote.dto.request.UserDto
import com.yasirabanuka.shopcart.data.remote.dto.response.LoginResponse


interface AuthRepository {
    suspend fun loginUser(loginDto: LoginDto): LoginResponse
    suspend fun registerUser(profileDto: ProfileDto): UserDto
    suspend fun userProfile(userId: Int): ProfileDto
}