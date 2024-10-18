package com.yasirabanuka.shopcart.domain.usecases

import com.yasirabanuka.shopcart.data.remote.dto.request.ProfileDto
import com.yasirabanuka.shopcart.data.remote.dto.request.UserDto
import com.yasirabanuka.shopcart.domain.repository.AuthRepository
import com.yasirabanuka.shopcart.presentation.screens.auth.ValidationResult
import com.yasirabanuka.shopcart.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        username: String,
        password: String,
        email: String,
        phone: String
    ): Flow<Resource<UserDto>> = flow {
        try {
            emit(Resource.Loading())
            val profileDto = ProfileDto(username, password, email, phone)
            val response = authRepository.registerUser(profileDto)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }


    companion object {
        fun validateRegisterRequest(
            username: String,
            password: String,
            email: String,
            phone: String
        ): ValidationResult {
            if (username.isBlank() && password.isBlank() && email.isBlank() && phone.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Username, Password, Email and Phone No cannot be blank"
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
            if (email.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Email cannot be blank"
                )
            }
            if (phone.isBlank()) {
                return ValidationResult(
                    successful = false,
                    error = "Phone No cannot be blank"
                )
            }
            if (email.isNotBlank()) {
                val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                val matches = EMAIL_REGEX.toRegex().matches(email)
                if (!matches) {
                    return ValidationResult(
                        successful = false,
                        error = "Email is not valid"
                    )
                }
            }
            if (phone.isNotBlank() && phone.length < 11) {
                return ValidationResult(
                    successful = false,
                    error = "Phone no is not valid"
                )
            }

            return ValidationResult(
                successful = true
            )
        }
    }
}