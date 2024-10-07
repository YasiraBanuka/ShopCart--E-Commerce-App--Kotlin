package com.yasirabanuka.shopcart.domain.usecases

import com.yasirabanuka.shopcart.data.remote.dto.request.LoginDto
import com.yasirabanuka.shopcart.data.remote.dto.response.LoginResponse
import com.yasirabanuka.shopcart.domain.repository.AuthRepository
import com.yasirabanuka.shopcart.presentation.screens.auth.ValidationResult
import com.yasirabanuka.shopcart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @created 30/06/2022 - 2:38 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(username: String, password: String): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val loginDto = LoginDto(username, password)
            val response = authRepository.loginUser(loginDto)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    companion object {
        fun validateLoginRequest(username: String, password: String): ValidationResult {
            if (username.isBlank() && password.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Username and Password cannot be blank"
                )
            }

            if (username.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Username cannot be blank"
                )
            }
            if (password.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Password cannot be blank"
                )
            }

            return ValidationResult(
                successful = true
            )
        }
    }
}