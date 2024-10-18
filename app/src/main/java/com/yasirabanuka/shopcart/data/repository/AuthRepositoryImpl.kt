package com.yasirabanuka.shopcart.data.repository

import com.yasirabanuka.shopcart.data.remote.BuyCartApi
import com.yasirabanuka.shopcart.data.remote.dto.request.LoginDto
import com.yasirabanuka.shopcart.data.remote.dto.request.ProfileDto
import com.yasirabanuka.shopcart.data.remote.dto.request.UserDto
import com.yasirabanuka.shopcart.data.remote.dto.response.LoginResponse
import com.yasirabanuka.shopcart.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val buyCartApi: BuyCartApi
): AuthRepository{
    override suspend fun loginUser(loginDto: LoginDto): LoginResponse {
        return buyCartApi.loginUser(loginDto)
    }

    override suspend fun registerUser(profileDto: ProfileDto): UserDto {
        return buyCartApi.registerUser(profileDto)
    }

    override suspend fun userProfile(userId: Int): ProfileDto {
        return buyCartApi.user(userId)
    }

}