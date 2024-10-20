package com.yasirabanuka.shopcart.data.remote

import com.yasirabanuka.shopcart.data.remote.dto.request.LoginDto
import com.yasirabanuka.shopcart.data.remote.dto.request.ProfileDto
import com.yasirabanuka.shopcart.data.remote.dto.request.UserDto
import com.yasirabanuka.shopcart.data.remote.dto.response.LoginResponse
import com.yasirabanuka.shopcart.data.remote.dto.response.ProductsDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface BuyCartApi {

    //Login
    @POST("auth/login")
    suspend fun loginUser(@Body loginDto: LoginDto): LoginResponse
    //Register
    @POST("users")
    suspend fun registerUser(@Body profileDto: ProfileDto): UserDto
    //All product
    @GET("products")
    suspend fun allProducts(): List<ProductsDto>
    //Single product
    @GET("products/{productId}")
    suspend fun singleProduct(@Path("productId") productId: Int): ProductsDto
    //All category
    @GET("products/categories")
    suspend fun allCategories(): List<String>
    //products in a category
    @GET("products/category/{category}")
    suspend fun productsInCategory(@Path("category") category: String): List<ProductsDto>
    //Get user
    @GET("users/{userId}")
    suspend fun user(@Path("userId") userId: Int): ProfileDto
}