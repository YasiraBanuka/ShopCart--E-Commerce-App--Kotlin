package com.yasirabanuka.shopcart.presentation.screens.profile

import com.yasirabanuka.shopcart.domain.model.PaymentInfo

/**
 * @created 18/07/2022 - 10:08 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
sealed class PaymentInfoEvents {
    data class SaveCard(val paymentInfo: PaymentInfo?): PaymentInfoEvents()
}
