package com.yasirabanuka.shopcart.presentation.screens.profile

import com.yasirabanuka.shopcart.domain.model.PaymentInfo

sealed class PaymentInfoEvents {
    data class SaveCard(val paymentInfo: PaymentInfo?): PaymentInfoEvents()
}
