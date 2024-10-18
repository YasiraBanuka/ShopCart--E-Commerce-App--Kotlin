package com.yasirabanuka.shopcart.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasirabanuka.shopcart.data.local.SharedPrefUtil
import com.yasirabanuka.shopcart.domain.model.PaymentInfo
import com.yasirabanuka.shopcart.domain.usecases.ProfileUseCase
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
class ProfileViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    var profileScreenState by mutableStateOf(ProfileScreenState())
        private set

    private val eventChannel = Channel<UIEvents>()
    val eventChannelFlow = eventChannel.receiveAsFlow()

    init {
        getUserProfile()
    }

    fun onEvent(events: PaymentInfoEvents) {
        when(events) {
            is PaymentInfoEvents.SaveCard -> {
                val paymentInfo = events.paymentInfo

                val result = ProfileUseCase.validatePaymentInfo(
                    cardNumber = paymentInfo?.cardNumber ?: "",
                    cardHolderName = paymentInfo?.cardHolderName ?: "",
                    cardExpiry = paymentInfo?.cardExpiryDate ?: "",
                    cardCVV = paymentInfo?.cardCVV ?: ""
                )

                if (result.successful) {
                    savePaymentInfo(events.paymentInfo!!)
                } else {
                    viewModelScope.launch {
                        eventChannel.send(UIEvents.ErrorEvent(result.error!!))
                    }
                }
            }
        }
    }

    private fun savePaymentInfo(paymentInfo: PaymentInfo) {
        val userProfile = profileScreenState.userProfile?.copy(paymentInfo = paymentInfo)

        profileUseCase.updateUserProfile(userProfile!!).onEach { result ->
            when(result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    eventChannel.send(UIEvents.SuccessEvent)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun logoutUser() = viewModelScope.launch {
        sharedPrefUtil.deleteAccessToken()
        eventChannel.send(UIEvents.SuccessEvent)
    }

    private fun getUserProfile() {
        profileUseCase.getUserProfile().onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    eventChannel.send(UIEvents.ErrorEvent(result.message!!))
                }
                is Resource.Success -> {
                    profileScreenState = profileScreenState.copy(userProfile = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}