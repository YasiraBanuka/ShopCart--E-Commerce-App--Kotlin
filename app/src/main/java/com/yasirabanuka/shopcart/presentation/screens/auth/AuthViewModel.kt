package com.yasirabanuka.shopcart.presentation.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasirabanuka.shopcart.data.local.SharedPrefUtil
import com.yasirabanuka.shopcart.domain.usecases.LoginUserUseCase
import com.yasirabanuka.shopcart.domain.usecases.RegisterUserUseCase
import com.yasirabanuka.shopcart.utils.Resource
import com.yasirabanuka.shopcart.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    var loadingState by mutableStateOf(false)
        private set

    private val authChannel = Channel<UIEvents>()
    val authChannelFlow = authChannel.receiveAsFlow()

    val isFirstAppLaunch = sharedPrefUtil.isFirstAppLaunch()

    fun saveFirstAppLaunch(value: Boolean) = sharedPrefUtil.saveFirstAppLaunch(value)

    private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun onAuthAction(events: AuthScreenEvents) {
        when(events) {
            is AuthScreenEvents.LoginClicked -> {
                val username = events.username
                val password = events.password

                val result = LoginUserUseCase.validateLoginRequest(username, password)

                if (result.successful) {
                    loginUser(username, password)
                } else {
                    viewModelScope.launch {
                        authChannel.send(UIEvents.ErrorEvent(result.error!!))
                    }
                }
            }
            is AuthScreenEvents.RegisterClicked -> {
                val username = events.username
                val password = events.password
                val email = events.email
                val phoneNo = events.phoneNo

                val result = RegisterUserUseCase.validateRegisterRequest(username, password, email, phoneNo)
                if (result.successful) {
                    registerUser(username, password, email, phoneNo)
                } else {
                    viewModelScope.launch {
                        authChannel.send(UIEvents.ErrorEvent(result.error!!))
                    }
                }
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        loginUserUseCase(username, password).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    loadingState = true
                }
                is Resource.Error -> {
                    loadingState = false
                    authChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    loadingState = false
                    saveUserAccessToken(result.data?.token!!)
                    authChannel.send(UIEvents.SuccessEvent)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun registerUser(
        username: String,
        password: String,
        email: String,
        phone: String
    ) {
        registerUserUseCase(username, password, email, phone).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    loadingState = true
                }
                is Resource.Error -> {
                    loadingState = false
                    authChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    loadingState = false
                    authChannel.send(UIEvents.SuccessEvent)
                }
            }
        }.launchIn(viewModelScope)
    }
}